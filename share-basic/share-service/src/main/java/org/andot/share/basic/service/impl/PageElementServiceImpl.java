package org.andot.share.basic.service.impl;

import lombok.RequiredArgsConstructor;
import org.andot.share.basic.entity.PageElement;
import org.andot.share.basic.service.PageElementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单元素服务实现
 * @author andot
 * @since 1.0.0
 */
@Service("pageElementService")
@RequiredArgsConstructor
public class PageElementServiceImpl implements PageElementService {
    @Override
    public List<PageElement> getPageElementList(Long menuId) {
        return null;
    }

    @Override
    public boolean savePageElement(PageElement PageElement) {
        return false;
    }

    @Override
    public boolean updatePageElement(Long id, PageElement PageElement) {
        return false;
    }

    @Override
    public boolean delPageElementById(Long id) {
        return false;
    }

    @Override
    public boolean delBatchPageElementById(List<Long> id) {
        return false;
    }
}
