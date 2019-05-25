package com.tianen.chen.source

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.tianen.chen.base.pojo.{MarketData, TimeScaleWarning, Trade, TradeWarning}
import com.tianen.chen.base.util.JsonUtil
import org.apache.flink.streaming.api.functions.source.{RichSourceFunction, SourceFunction}

import scala.util.Random

/**
  * @author :tianen
  * @date :Created in 2019/4/24 18:20
  * @description :${description}
  * @version : $version$
  */
class KafkaSource extends RichSourceFunction[String]{

  var isRunning = true

  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    val jsonUtil = new JsonUtil
    var random: Double = new Random().nextDouble * 1000
    val instrumentID = Array("cu1910", "al1907", "zn1909", "au1912")
    val direction = Array("0","1")
    val investorID = Array("foo","bar")
    val formatter = new SimpleDateFormat("yyyyMMdd")
    while(isRunning){
      Thread.sleep(2000)

      val marketData = new MarketData
      marketData.setInstrumentID(instrumentID(new Random().nextInt(4)))
      random = random + new Random().nextDouble * 21 - 10
      marketData.setLastPrice(random)
      marketData.setUpdateTime(String.valueOf(System.currentTimeMillis))
      val _marketData = jsonUtil.marketData2JsonPkgStr(marketData)

      val trade = new Trade

      trade.setDirection(direction(new Random().nextInt(2)))
      trade.setExchangeID("SHFE")
      trade.setInstrumentID(instrumentID(new Random().nextInt(4)))
      trade.setInvestorID(investorID(new Random().nextInt(2)))
//      trade.setTradeDate(formatter.format(new Date))
      val day = Calendar.getInstance
      day.add(Calendar.DATE,-5)
      trade.setTradeDate(formatter.format(day.getTime))
      trade.setTradeVolume(new Random().nextInt(300))
      trade.setTradePrice(new Random().nextInt(1200)+20)

      val _trade1 = jsonUtil.trade2JsonPkgStr(trade)
      trade.setDirection(direction(new Random().nextInt(2)))
      val _trade2 = jsonUtil.trade2JsonPkgStr(trade)

      val tradeWarning = new TradeWarning
      tradeWarning.setInvestorID(investorID(new Random().nextInt(2)))
      tradeWarning.setScale1(1)
      tradeWarning.setScale2(5)
      tradeWarning.setScale3(10)
      tradeWarning.setSelfTradeConsolidate("1")
      tradeWarning.setExchangeID("SHFE")

      val _tradeWarning = jsonUtil.tradeWarning2JsonPkgStr(tradeWarning)

      val timeScaleWarning = new TimeScaleWarning
      timeScaleWarning.setInstrumentID(instrumentID(new Random().nextInt(4)))
      val _timeScaleWarning = jsonUtil.timeScaleWarning2JsonPkgStr(timeScaleWarning)

      ctx.collect(_marketData)
      ctx.collect(_trade1)
      ctx.collect(_trade2)

      ctx.collect(_tradeWarning)
      ctx.collect(_timeScaleWarning)
    }
  }

  override def cancel(): Unit = {isRunning = false}
}
