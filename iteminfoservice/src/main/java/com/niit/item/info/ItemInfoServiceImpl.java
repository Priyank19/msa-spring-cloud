package com.niit.item.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {
	private static Logger logger = LoggerFactory.getLogger(ItemInfoServiceImpl.class);

	@Override
	public ItemInfo getItemInfo(int id) {
		logger.debug("Mock ItemInfo Object");
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setId(id);
		itemInfo.setCategory("book");
		itemInfo.setDescription("This is a book on MSA");
		itemInfo.setImage("http://localhost/abc.jpg");
		itemInfo.setName("Microservices Architecture");
		itemInfo.setPrice(20.00);
		return itemInfo;
	}

}
