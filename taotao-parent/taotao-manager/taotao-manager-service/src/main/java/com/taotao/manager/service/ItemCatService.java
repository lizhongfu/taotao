package com.taotao.manager.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

public interface ItemCatService {
    List<EUTreeNode> getItemCatList(long parentId);
}
