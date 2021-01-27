package zdc.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class IntegrationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int i = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(i);
        System.out.println(i);
        System.out.println(i);
    }


    public int maxProfit(int[] prices) {
        int length = prices.length;
        int sum =0;
        for( int i =0 ; (i <length) && (i+1<length)  ; i++ ){
           if  (prices[i]< prices[i+1]){
               sum+=(prices[i+1]-prices[i]);
           }

        }
        return sum;
    }



}
