package com.tianen.chen

import com.tianen.chen.base.conf.Config
import com.tianen.chen.base.entity.JsonType
import com.tianen.chen.base.util.JsonUtil
import com.tianen.chen.sink.MongoSink
import com.tianen.chen.source.KafkaSource
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}

/**
  * @author :tianen
  * @date :Created in 2019/4/24 18:17
  * @description :${description}
  * @version : $version$
  */
object SimulationData {
  val jsonUtil = new JsonUtil
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment.setParallelism(1)
    val stream :DataStream[String] = env
      .addSource(new KafkaSource)

    val logStream :DataStream[String] = env.addSource(new FlinkKafkaConsumer[String]("te-log",new SimpleStringSchema,Config.getProp))

    val resStream1 :DataStream[String] = logStream.map(x=>{
      jsonUtil.any2JsonPkgStr(x,JsonType.Log)
    })

    val tradeStream:DataStream[String] = stream.filter({jsonUtil.str2JsonPkg(_).getType match {
      case JsonType.Trade => true
      case _ => false
    }})

    val marketDataStream :DataStream[String]= stream.filter({jsonUtil.str2JsonPkg(_).getType match {
      case JsonType.MarketData => true
      case _ => false
    }})

    val tradeWarningStream :DataStream[String]= stream.filter({jsonUtil.str2JsonPkg(_).getType match {
      case JsonType.TradeWarning => true
      case _ => false
    }})

    val timeScaleWarningStream :DataStream[String] = stream.filter({jsonUtil.str2JsonPkg(_).getType match {
      case JsonType.TimeScaleWarning => true
      case _ => false
    }})

    tradeStream.map(x=>{
      jsonUtil.str2JsonPkg(x).getJson
    }).addSink(new MongoSink("trade"))

    marketDataStream.map(x=>{
      jsonUtil.str2JsonPkg(x).getJson
    }).addSink(new MongoSink("quotes"))

    val resStream:DataStream[String] = resStream1 union marketDataStream union tradeStream union tradeWarningStream union timeScaleWarningStream

    resStream.addSink(new FlinkKafkaProducer[String]("127.0.0.1:29092","te-all",new SimpleStringSchema))

    env.execute()
  }
}
