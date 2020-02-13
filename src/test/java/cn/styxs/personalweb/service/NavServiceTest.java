package cn.styxs.personalweb.service;

import cn.styxs.personalweb.model.NavTabItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NavServiceTest {
    @Autowired
    NavService navService;

    @Test
    public void queryNavTabs() {
        List<NavTabItem> items = navService.getTabItems();
        log.info(""+ items.size());
        for (NavTabItem item : items) {
            log.info(item.toString());
        }
    }
}