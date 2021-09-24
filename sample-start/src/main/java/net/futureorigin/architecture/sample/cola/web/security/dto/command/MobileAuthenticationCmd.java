package net.futureorigin.architecture.sample.cola.web.security.dto.command;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * MobileAuthenticationCmd
 * <p></p>
 *
 * @author Leander Lee on 2021/9/11.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MobileAuthenticationCmd extends BaseAuthenticationCmd {

    @NotBlank
    @Size(min = 1, max = 50)
    private String mobile;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;


}
