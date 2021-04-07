package com.yadavmo.demo

import com.yadavmo.demo.model.Customer
import com.yadavmo.demo.model.Customer.CustomerType
import com.yadavmo.demo.model.Dummy
import com.yadavmo.demo.model.Instrument
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.kie.api.io.ResourceType
import org.kie.api.runtime.KieSession
import org.kie.internal.builder.KnowledgeBuilderFactory
import org.kie.internal.io.ResourceFactory
import java.io.StringReader


class DemoApplicationTests {

	private var kSession: KieSession? = null

	@Before
	fun setup() {
		val resources =
			listOf(ResourceFactory.newClassPathResource("com/yadavmo/rules/Discount.xls", javaClass))
		kSession = DroolsConfig().getKieSession(resources)
	}

	@Test
	fun rough(){
		val drl1 = DroolsConfig().getDrlFromExcel("com/yadavmo/rules/InstrumentTypeRules2.xls")
		val drl2 = DroolsConfig().getDrlFromExcel("com/yadavmo/rules/PriceTypeRules2.xls")

		println(drl1)
		println(drl2)

		// verify
		val kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder()
		kBuilder.add(ResourceFactory.newReaderResource(StringReader(drl1!!)), ResourceType.DRL)
		kBuilder.add(ResourceFactory.newReaderResource(StringReader(drl2!!)), ResourceType.DRL)
		if(kBuilder.hasErrors()){
			println("DRL file is erroneous")
			println("errors :")
			kBuilder.errors.forEach { println(it.message) }
		}
	}

	@Test
	fun roughAgain(){
		val resources= listOf(
			ResourceFactory.newClassPathResource("com/yadavmo/rules/InstrumentTypeRules2.xls", javaClass),
			ResourceFactory.newClassPathResource("com/yadavmo/rules/PriceTypeRules2.xls", javaClass),
		)
		kSession = DroolsConfig().getKieSession(resources)
		val dummies = mutableListOf(
			Dummy(null,0, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = true,
				isListed = false,1233, listOf(1,2), null),
			Dummy(null,19,
				listOf(10, 96, 59, 60, 65, 97, 110, 123, 129, 6, 54, 127),
				listOf(1,2), 3,3,3,
				isTRS = false, isListed = false,1233, listOf(1,2),
				null),
			Dummy(null,0, listOf(1,2),
				listOf(70, 72, 85, 169, 247, 276, 336), 3,
				3,3,isTRS = false, isListed = false,
				1233, listOf(1,2), null),
//			Dummy(null,0, listOf(1,2), listOf(1,2),
//				3,3,3,isTRS = true,
//				isListed = false,1233, listOf(1,2), null),
			Dummy(null,5, listOf(0), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(1,2), null),
			Dummy(null,11, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(1,2), null),
			Dummy(null,14, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(1,2), null),
			Dummy(null,4, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(1,2), null),


			Dummy(null,0, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = true,
				isListed = false,123, listOf(1,2), null),
			Dummy(null,11, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(2,4), null),
			Dummy(null,11, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233,
				listOf(1,3,5,6,7,8,9,10,11,12,13,14,16,17), null),
			Dummy(null,14, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(2,4), null),
			Dummy(null,14, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233,
				listOf(1,3,5,6,7,8,9,10,11,12,13,14,16,17), null),
			Dummy(null,4, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233, listOf(2,4), null),
			Dummy(null,4, listOf(1,2), listOf(1,2),
				3,3,3,isTRS = false,
				isListed = false,1233,
				listOf(1,3,5,6,7,8,9,10,11,12,13,14,16,17), null),
		)
		dummies.forEach { kSession!!.insert(it) }
		kSession!!.fireAllRules()
		assert(dummies[0].instrumentType == Instrument.InstrumentType.IS_SWAP_TRADE)
		assert(dummies[1].instrumentType == Instrument.InstrumentType.IS_CDS_TRADE)
		assert(dummies[2].instrumentType == Instrument.InstrumentType.IS_CFD_TRADE)
		assert(dummies[3].instrumentType == Instrument.InstrumentType.IS_GOVERNMENT_BOND_TRADE)
		assert(dummies[4].instrumentType == Instrument.InstrumentType.ASSET_STRIP_TYPE)
		assert(dummies[5].instrumentType == Instrument.InstrumentType.NON_CONVERTIBLE_DEBT_TYPE)
		assert(dummies[6].instrumentType == Instrument.InstrumentType.CONVERT_TYPE)

		for (i in 0 until dummies.size){
			dummies[i] = Dummy(dummies[i])
		}

		dummies.forEach { kSession!!.insert(it) }
		kSession!!.fireAllRules()
		assert(dummies[7].priceType == Instrument.PriceType.PCTG)
		assert(dummies[8].priceType == Instrument.PriceType.PCTG)
		assert(dummies[9].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
		assert(dummies[10].priceType == Instrument.PriceType.PCTG)
		assert(dummies[11].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
		assert(dummies[12].priceType == Instrument.PriceType.PCTG)
		assert(dummies[13].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
	}

	@Test
	fun demo(){
//		println(DroolsConfig().getDrlFromExcel("com/yadavmo/rules/Discount.xls"))
		val drl1 = DroolsConfig().getDrlFromExcel("com/yadavmo/rules/InstrumentTypeRules.xls")
		val drl2 = DroolsConfig().getDrlFromExcel("com/yadavmo/rules/PriceTypeRules.xls")

		println(drl1)
		println(drl2)

		// verify
		val kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder()
		kBuilder.add(ResourceFactory.newReaderResource(StringReader(drl1!!)), ResourceType.DRL)
		kBuilder.add(ResourceFactory.newReaderResource(StringReader(drl2!!)), ResourceType.DRL)
		if(kBuilder.hasErrors()){
			println("DRL file is erroneous")
			println("errors :")
			kBuilder.errors.forEach { println(it.message) }
		}
	}

	@Test
	@Throws(Exception::class)
	fun demoAgain() {
		val resources= listOf(
			ResourceFactory.newClassPathResource("com/yadavmo/rules/InstrumentTypeRules.xls", javaClass),
			ResourceFactory.newClassPathResource("com/yadavmo/rules/PriceTypeRules.xls", javaClass),
		)
		kSession = DroolsConfig().getKieSession(resources)
		val instruments = mutableListOf(
			Instrument(null,"*","*","*",
				"*","*","*","Yes",
				"*","None",null, null),
			Instrument(null,"19","big_array",null,
				null,null,null,"*",
				"*","None",null, null),
			Instrument(null,"*","*","big_array_2",
				"*","*","*","*",
				"*","None",null, null),
			Instrument(null,"5","0","*",
				"*","*","*","Yes",
				"*","None",null, null),
			Instrument(null,"5","0","*",
				"*","*","*","No",
				"*","None",null, null),
			Instrument(null,"11","*","*",
				"*","*","*","*",
				"*","None",null, null),
			Instrument(null,"14","*","*",
				"*","*","*","*",
				"*","None",null, null),
			Instrument(null,"4","*","*",
				"*","*","*","*",
				"*","None",null, null),
			Instrument(null,"*","*","*",
				"*","*","*","Yes",
				"*","*",null, null),
			Instrument(null,"14","*","*",
				"*","*","*","*",
				"*","*","2,4", null),
			Instrument(null,"14","*","*",
				"*","*","*","*",
				"*","*","1,3,5,6,7,8,9,10,11,12,13,14,16,17", null),
			Instrument(null,"4","*","*",
				"*","*","*","*",
				"*","*","2,4", null),
			Instrument(null,"4","*","*",
				"*","*","*","*",
				"*","*","1,3,5,6,7,8,9,10,11,12,13,14,16,17", null),
			Instrument(null,"11","*","*",
				"*","*","*","*",
				"*","*","2,4", null),
			Instrument(null,"11","*","*",
				"*","*","*","*",
				"*","*","1,3,5,6,7,8,9,10,11,12,13,14,16,17", null),
		)
		instruments.forEach { kSession!!.insert(it) }
		kSession!!.fireAllRules()
		assert(instruments[0].instrumentType == Instrument.InstrumentType.IS_SWAP_TRADE)
		assert(instruments[1].instrumentType == Instrument.InstrumentType.IS_CDS_TRADE)
		assert(instruments[2].instrumentType == Instrument.InstrumentType.IS_CFD_TRADE)
		assert(instruments[3].instrumentType == Instrument.InstrumentType.IS_GOVERNMENT_BOND_SWAP_TRADE)
		assert(instruments[4].instrumentType == Instrument.InstrumentType.IS_GOVERNMENT_BOND_TRADE)
		assert(instruments[5].instrumentType == Instrument.InstrumentType.ASSET_STRIP_TYPE)
		assert(instruments[6].instrumentType == Instrument.InstrumentType.NON_CONVERTIBLE_DEBT_TYPE)
		assert(instruments[7].instrumentType == Instrument.InstrumentType.CONVERT_TYPE)
		for (i in 0 until instruments.size){
			instruments[i] = Instrument(instruments[i])
		}
		instruments.forEach { kSession!!.insert(it) }
		kSession!!.fireAllRules()
		assert(instruments[0].priceType == Instrument.PriceType.PNDG)
		assert(instruments[8].priceType == Instrument.PriceType.PCTG)
		assert(instruments[9].priceType == Instrument.PriceType.PCTG)
		assert(instruments[10].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
		assert(instruments[11].priceType == Instrument.PriceType.PCTG)
		assert(instruments[12].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
		assert(instruments[13].priceType == Instrument.PriceType.PCTG)
		assert(instruments[14].priceType == Instrument.PriceType.MNTRY_VAL_AMOUNT)
	}

	@Test
	@Throws(Exception::class)
	fun giveIndvidualRecent_whenFireRule_thenCorrectDiscount() {
		val customer = Customer(CustomerType.INDIVIDUAL, 1)
		kSession!!.insert(customer)
		kSession!!.fireAllRules()
		assertEquals(customer.discount, 5)
	}

	@Test
	@Throws(Exception::class)
	fun giveBusinessAny_whenFireRule_thenCorrectDiscount() {
		val customer = Customer(CustomerType.BUSINESS, 0)
		kSession!!.insert(customer)
		kSession!!.fireAllRules()
		assertEquals(customer.discount, 20)
	}

}
