package com.motorolaintl.pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cinatic.TimeHelper;
import com.ebn.automation.core.WbElement;
import com.ebn.automation.core.WbDriverManager;

public class PageShippingInformation extends PageBase {

	public PageShippingInformation() {
		PATH = "checkout/onepage/#";
	}

	public WbElement getLogoIcon() {
		String xpath = "//a[@class='logo']";
		return new WbElement(By.xpath(xpath),"Logo Icon");
	}

	public WbElement getEmailAddressTbx() {
		String id = "login-email";
		return new WbElement(By.id(id),"Login Email Text box");
	}

	public WbElement getFirstNameTbx() {
		String id = "shipping:firstname";
		return new WbElement(By.id(id),"First Name Text box");
	}

	public WbElement getLastNameTbx() {
		String id = "shipping:lastname";
		return new WbElement(By.id(id),"Last Name Text box");
	}

	public WbElement getAddress1Tbx() {
		String id = "shipping:street1";
		return new WbElement(By.id(id),"Address Text box 1");
	}

	public WbElement getAddress2Tbx() {
		String id = "shipping:street2";
		return new WbElement(By.id(id),"Address Text box 2");
	}

	public WbElement getZipCodeTbx() {
		String id = "shipping:postcode";
		return new WbElement(By.id(id),"Zip Code Text box");
	}

	public WbElement getCityTbx() {
		String id = "shipping:city";
		return new WbElement(By.id(id),"City Text box");
	}

	public WbElement getCountryTbx() {
		String id = "shipping:country_label";
		return new WbElement(By.id(id),"Country Text box");
	}

	public WbElement getStateDropBox() {
		String id = "shipping:region_id";
		return new WbElement(By.id(id),"State Drop Box");
	}

	public WbElement getTelephoneTbx() {
		String id = "shipping:telephone";
		return new WbElement(By.id(id),"Telephone Text box");
	}

	public List<WbElement> getShippingMethodCkb() {
		String id = "s_method_freeshipping_freeshipping";
		return WbDriverManager.findElements(By.id(id));
	}

	public WbElement getContinueBtn() {
		String xpath = "//button[@class='button btn-proceed-payment']";
		return new WbElement(By.xpath(xpath),"Continue Button");
	}
	public WbElement getCloseErrorBtn() {
		String xpath = "//div[@class='modal-ac fade in']//button";
		return new WbElement(By.xpath(xpath),"Close Button");
	}
	
	public WbElement getTitleErrorTxt() {
		String xpath = "//div[@class='modal-ac fade in']/div[@class='modal-ac-header']/h3";
		return new WbElement(By.xpath(xpath),"Title Error");
	}

	public void clickLogoIcon() {
		getLogoIcon().click();
	}

	public void inputEmailAddress(String email) {
		getEmailAddressTbx().sendKeys(email);
	}

	public void inputFirstName(String firstname) {
		getFirstNameTbx().sendKeys(firstname);
	}

	public void inputLastName(String lastname) {
		getLastNameTbx().sendKeys(lastname);
	}

	public void inputAddressStreet1(String address) {
		getAddress1Tbx().sendKeys(address);
	}

	public void inputAddressStreet2(String address) {
		getAddress2Tbx().sendKeys(address);
	}

	public void inputZipCode(String zipCode) {
		getZipCodeTbx().sendKeys(zipCode);
	}

	public void selectRegion(String state) {
		WebElement ele = getStateDropBox();
		Select select = new Select(ele);
		select.selectByVisibleText(state);
	}

	public void inputTelephone(String telephone) {
		getTelephoneTbx().sendKeys(telephone);
	}

	public void inputCity(String city) {
		getCityTbx().sendKeys(city);
	}

	public void clickContinueBtn() {
		getContinueBtn().click();
		TimeHelper.sleep(5000);
	}

	public void verifyShippingMethodIsDisplay() {
		for (WbElement ele : getShippingMethodCkb()) {
			assertTrue(ele.isDisplayed());
		}
	}

	public void clickContinueBtnAndVerify() {
		clickContinueBtn();
		WbDriverManager.waitForPageLoad();
		TimeHelper.sleep(9000);
		PagePayment pagePayment = new PagePayment();
		pagePayment.verifyUrl();
		WbDriverManager.backPreviousPage();
	}

	public void clickLogoIconAndVerify() {
		clickLogoIcon();
		WbDriverManager.waitForPageLoad();
		PageCart pageCart = new PageCart();
		pageCart.verifyUrl();
		WbDriverManager.backPreviousPage();
	}
	
	
	
	public List<String> getListFieldErrorMessage() {
		List<String> listField = new ArrayList<String>();
		String xpath = "//div[@class='modal-ac fade in']//ul/li";
		List<WbElement> errorElement = WbDriverManager.findElements(By.xpath(xpath));
		for(WbElement ele : errorElement) {
			listField.add(ele.getText());
		}
		return listField;
	}
	
	public void clickCloseError() {
		getCloseErrorBtn().clickByJavaScripts();
	}
	
	public String getTitleErrorMessage() {
		return getTitleErrorTxt().getText();
	}

}