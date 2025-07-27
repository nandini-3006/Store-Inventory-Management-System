package com.nandini.management.products;

import com.nandini.management.loss.LossEntity;
import com.nandini.management.loss.LossRepository;
import com.nandini.management.profit.DailyProfit;
import com.nandini.management.profit.DailyProfitRepository;
import com.nandini.management.profit.MonthlyProfit;
import com.nandini.management.profit.MonthlyProfitRepository;
import com.nandini.management.sales.SalesEntity;
import com.nandini.management.sales.SalesRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;
    private final DailyProfitRepository dailyProfitRepository;
    private final MonthlyProfitRepository monthlyProfitRepository;
    private final LossRepository lossRepository;
    public ProductService(ProductRepository productRepository,SalesRepository salesRepository,DailyProfitRepository dailyProfitRepository,MonthlyProfitRepository monthlyProfitRepository,LossRepository lossRepository) {
        this.productRepository = productRepository;
        this.salesRepository=salesRepository;
        this.dailyProfitRepository=dailyProfitRepository;
        this.monthlyProfitRepository=monthlyProfitRepository;
        this.lossRepository=lossRepository;
    }
    public ProductEntity addProduct(Double price,Long quantity,String brand,String name,LocalDate expiry,Double profit,Double discount){
        ProductEntity product = new ProductEntity();
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setBrand(brand);
        product.setName(name);
        product.setExpiry(expiry);
        product.setProfit(profit);
        product.setDiscount(discount);
        product.setDiscount_status("at the regular price");
        product.setDiscount_bool(false);
        return productRepository.save(product);
    }

    public void billing(List<BillingDTO> list){
        int size=list.size();
        for(int i=0;i<size;i++){
            BillingDTO product =list.get(i);
            ProductEntity set=productRepository.findFirstByNameAndBrandOrderByExpiryAsc(product.getName(),product.getBrand()).orElseThrow(() -> new RuntimeException("Product not found"));
            set.setQuantity(set.getQuantity()-product.getQuantity());
            Optional<DailyProfit> set3 = dailyProfitRepository.findByDate(LocalDate.now());
            if(set3.isPresent()){
                DailyProfit  profitEntry = set3.get();
                profitEntry.setProfit(profitEntry.getProfit()+(product.getQuantity()*set.getProfit()));
            dailyProfitRepository.save(profitEntry);
            }
            else{
                DailyProfit  profitEntry = new DailyProfit();
                profitEntry.setProfit(product.getQuantity()*set.getProfit());
                profitEntry.setDate(LocalDate.now());
                dailyProfitRepository.save(profitEntry);
            }
            Optional<MonthlyProfit> set4 = monthlyProfitRepository.findByMonAndYear(LocalDate.now().getMonthValue(),(long)LocalDate.now().getYear());
            if(set4.isPresent()){
                MonthlyProfit  MonthlyprofitEntry = set4.get();
                MonthlyprofitEntry.setProfit(MonthlyprofitEntry.getProfit()+(product.getQuantity()*set.getProfit()));
                monthlyProfitRepository.save(MonthlyprofitEntry);
            }
            else{
                MonthlyProfit  MonthlyprofitEntry = new MonthlyProfit();
                MonthlyprofitEntry.setProfit(product.getQuantity()*set.getProfit());
                MonthlyprofitEntry.setMon(LocalDate.now().getMonthValue());
                MonthlyprofitEntry.setYear((long)LocalDate.now().getYear());
                monthlyProfitRepository.save(MonthlyprofitEntry);
            }
            if(set.getQuantity()<=0){
                productRepository.delete(set);
            }
            else {
                productRepository.save(set);
            }
            Optional<SalesEntity> set2 = salesRepository.findByNameAndBrandAndMonthAndYearAndDiscount_bool(set.getName(),set.getBrand(),LocalDate.now().getMonthValue(),(long)LocalDate.now().getYear(),set.getDiscount_bool());
            if(set2.isPresent()){
                SalesEntity sale = set2.get();
                sale.setQuantity(sale.getQuantity()+product.getQuantity());
                sale.setTotal(sale.getTotal() + product.getQuantity() * set.getPrice());
                salesRepository.save(sale);
            }
            else{
                SalesEntity sale = new SalesEntity();
                sale.setPrice(set.getPrice());
                sale.setQuantity(product.getQuantity());
                sale.setBrand(set.getBrand());
                sale.setName(set.getName());
                sale.setMonth(LocalDate.now().getMonthValue());
                sale.setYear((long)LocalDate.now().getYear());
                sale.setTotal(product.getQuantity()*set.getPrice());
                sale.setDiscount(set.getDiscount());
                sale.setDiscount_bool(set.getDiscount_bool());
                salesRepository.save(sale);
            }
        }
    }
    @Scheduled(fixedRate = 1000)
    public void expired(){
        LocalDate today=LocalDate.now();
        List<ProductEntity> product = productRepository.findAll();
        int s=product.size();
        for(int i=0;i<s;i++) {
            if (today.isAfter(product.get(i).getExpiry())) {
                LossEntity loss_items=new LossEntity();
                loss_items.setQuantity(product.get(i).getQuantity());
                loss_items.setStatus("Expired");
                loss_items.setBrand(product.get(i).getBrand());
                loss_items.setName(product.get(i).getName());
                loss_items.setDate(LocalDate.now());
                if(product.get(i).getDiscount_bool()){
                    loss_items.setLoss(product.get(i).getPrice() * 100.0 / (100.0 - product.get(i).getDiscount()) * product.get(i).getQuantity());
                }
                else {
                    loss_items.setLoss(product.get(i).getPrice() * product.get(i).getQuantity());
                }
                lossRepository.save(loss_items);
                productRepository.delete(product.get(i));
            }
            if (ChronoUnit.DAYS.between(LocalDate.now(), product.get(i).getExpiry()) == 15&&product.get(i).getDiscount_bool()==false) {
               product.get(i).setPrice(product.get(i).getPrice()-((product.get(i).getDiscount()/100)*product.get(i).getPrice()));
               product.get(i).setDiscount_status(product.get(i).getDiscount()+"%off");
                product.get(i).setProfit(product.get(i).getProfit()-(product.get(i).getDiscount()/100)*product.get(i).getPrice());
                product.get(i).setDiscount_bool(true);
                productRepository.save(product.get(i));
            }

        }
    }
    public void damaged(List<DamagedDTO> list){
        int s=list.size();
        for(int i=0;i<s;i++) {
            ProductEntity set = productRepository.findByNameAndBrandAndExpiry(list.get(i).getName(),list.get(i).getBrand(),list.get(i).getExpiry()).orElseThrow(() -> new RuntimeException("Product not found"));
            set.setQuantity(set.getQuantity() - list.get(i).getQuantity());
            if(set.getQuantity()<=0){
                LossEntity loss_items=new LossEntity();
                loss_items.setQuantity(set.getQuantity());
                loss_items.setStatus("Damaged");
                loss_items.setBrand(set.getBrand());
                loss_items.setName(set.getName());
                loss_items.setDate(LocalDate.now());
                loss_items.setLoss(set.getPrice()*set.getQuantity());
                lossRepository.save(loss_items);
                productRepository.delete(set);
            }
            else {
                productRepository.save(set);
            }
        }
    }
    public List<LowStockDTO> low_stock(){
        return productRepository.findLowStock();
    }

}