package br.com.erudio.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsupportedConversionException;
import br.com.erudio.exception.UnsupportedMathOperationException;
import br.com.erudio.util.NumberUtil;
import br.com.erudio.util.SimpleMath;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)throws UnsupportedConversionException {
		double d1 = NumberUtil.strToDouble(numberOne);
		double d2 = NumberUtil.strToDouble(numberTwo);
		return SimpleMath.sum(d1, d2);
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)throws UnsupportedConversionException {
		double d1 = NumberUtil.strToDouble(numberOne);
		double d2 = NumberUtil.strToDouble(numberTwo);
		return SimpleMath.sub(d1, d2);
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)throws UnsupportedConversionException {
		double d1 = NumberUtil.strToDouble(numberOne);
		double d2 = NumberUtil.strToDouble(numberTwo);
		return SimpleMath.mult(d1, d2);
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)throws UnsupportedConversionException, UnsupportedMathOperationException {
		double d1 = NumberUtil.strToDouble(numberOne);
		double d2 = NumberUtil.strToDouble(numberTwo);
		return SimpleMath.div(d1, d2);
	}
	
	@RequestMapping(value = "/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
			)throws UnsupportedConversionException {
		double d1 = NumberUtil.strToDouble(numberOne);
		double d2 = NumberUtil.strToDouble(numberTwo);
		return SimpleMath.mean(d1, d2);
	}
	
	@RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable("number") String number)throws UnsupportedConversionException {
		double d = NumberUtil.strToDouble(number);
		return SimpleMath.sqrt(d);
	}
	
	@RequestMapping(value = "/pow/{number}/{p}", method = RequestMethod.GET)
	public Double pow(@PathVariable("number") String number, @PathVariable("p") String p)throws UnsupportedConversionException {
		double d1 = NumberUtil.strToDouble(number);
		double d2 = NumberUtil.strToDouble(p);
		return SimpleMath.pow(d1, d2);
	}
	
}
