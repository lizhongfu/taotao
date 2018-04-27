package com.taotao.manager.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.manager.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long ItemId);
    EUDataGridResult getItemList(int page, int rows);
//    TaotaoResult creatItem(TbItem item);
    TaotaoResult creatItem(TbItem item, String desc, String itemParam) throws Exception;
}
