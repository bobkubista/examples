package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;



public interface Transactions {
    
    int size();
    
    Transactions ofLast(Duration duration);
                    
    Transactions since(Instant fromTime);
    
    Transactions failed();
    
    Transactions running();
    
    Duration percentile(int percent);    
}