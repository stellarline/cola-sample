package net.futureorigin.architecture.sample.cola.config;

import net.futureorigin.architecture.sample.cola.id.IdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomIdentifierGenerator implements IdentifierGenerator {

    public CustomIdentifierGenerator() {
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return IdGenerator.nextId();
    }
}
