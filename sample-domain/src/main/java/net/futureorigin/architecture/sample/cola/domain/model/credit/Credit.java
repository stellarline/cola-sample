package net.futureorigin.architecture.sample.cola.domain.model.credit;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Credit
 * <p></p>
 *
 * @author Leander Lee on 2021/9/5.
 */
@Getter
@Setter
@ToString
@Entity
public class Credit {
    public static final double GIVING_BALANCE = 1000;
}
