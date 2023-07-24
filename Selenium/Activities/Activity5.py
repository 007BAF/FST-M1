# Import webdriver from selenium
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())
with webdriver.Firefox(service=service) as driver:
    actions = ActionChains(driver)
    driver.get("https://www.training-support.net/selenium/input-events")
    # Print the title of the new page
    print("New page title is: ", driver.title)
    actions.click().perform()
    active_side = driver.find_element(By.CLASS_NAME, "active")

    print("Active side number after left click: ", active_side.text)
    actions.double_click().perform()
    # Find the side that is active
    active_side = driver.find_element(By.CLASS_NAME, "active")
    # Print the number shown
    print("Active side number after double click: ", active_side.text)

    # Perform a RIGHT CLICK action
    actions.context_click().perform()
    # Find the side that is active
    active_side = driver.find_element(By.CLASS_NAME, "active")
    # Print the number shown
    print("Active side number after right click: ", active_side.text)