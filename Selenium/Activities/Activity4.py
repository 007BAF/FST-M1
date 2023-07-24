# Import webdriver from selenium
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())
with webdriver.Firefox(service=service) as driver:
    driver.get("https://training-support.net/selenium/target-practice")
    # Print the title of the new page
    print("New page title is: ", driver.title)
    threeHeader=driver.find_element(By.XPATH,"//h3[@id='third-header']")
    print("New page title is: ", threeHeader.text)
    FiveHeader=driver.find_element(By.XPATH,"//h5[@class='ui green header']")
    print("New page title is: ", FiveHeader.text)