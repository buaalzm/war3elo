package com.war3elo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootTest
@MapperScan("com.war3elo.mapper")
class War3eloApplicationTests {

    @Test
    void contextLoads() {
    }

}
