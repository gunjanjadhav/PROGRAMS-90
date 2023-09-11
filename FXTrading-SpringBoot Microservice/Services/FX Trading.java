package com.FXTrading.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;
import com.FXTrading.model.FXTradingEntity;

@Component
public class FXTradingLogic  {
	
	private List<FXTradingEntity> bookedTradesEntities=new ArrayList<>();
	

	
	public Object doTrade(FXTradingEntity fxTradingEntity){
		
		Map<String, String> errorsByUser=new HashMap<>();
		
		if(fxTradingEntity.getCustomerName().isBlank())
		{
			errorsByUser.put("Customer-Name", "Customer name cannot be empty");
		}
		if(fxTradingEntity.getUsd_Amount()<0)
		{
			errorsByUser.put("USD-Amount", "Amount cannot be Negative");
		}
		if(!fxTradingEntity.getCurrencyPair().equals("USDINR"))
		{
			errorsByUser.put("Currency-Pair", "Currency pair is not USDINR");
		}
			
		if(errorsByUser.isEmpty())
		{
			String indianAmount= ""+(fxTradingEntity.getUsd_Amount()*66);
			fxTradingEntity.setIndianAmount(indianAmount);
	       
			this.bookedTradesEntities.add(fxTradingEntity);
			return fxTradingEntity;
		}
		else {
			return errorsByUser;
		}
		
	}
	
	public List<FXTradingEntity> getTradeList()
	{
		return this.bookedTradesEntities;
	}

