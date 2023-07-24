import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope="class")
def setup():
    driver = webdriver.Firefox()
    driver.get("http://alchemy.hguy.co/orangehrm")
    wait = WebDriverWait(driver, 10)
    yield driver, wait
    driver.close()

@pytest.mark.usefixtures("setup")
class TestActivity7:

    @pytest.mark.priority(1)
    def test_login(self, setup):
        driver, wait = setup
        username = driver.find_element(By.ID, "txtUsername")
        password = driver.find_element(By.ID, "txtPassword")
        login_btn = driver.find_element(By.ID, "btnLogin")
        username.send_keys("orange")
        password.send_keys("orangepassword123")
        login_btn.click()
        image = driver.find_element(By.XPATH, "//img[@alt='OrangeHRM']")
        print("ORANGE HRM image: " + image.text)

    @pytest.mark.priority(2)
    def test_qualifications(self, setup):
        driver, wait = setup
        view_details = driver.find_element(By.XPATH, "//a[@id='menu_pim_viewMyDetails']")
        view_details.click()
        view_details.click()
        print("myinfo")
        qualifications = driver.find_element(By.XPATH, "//a[contains(@href,'viewQualifications')]")
        qualifications.click()
        driver.find_element(By.ID, "addWorkExperience").click()
        driver.find_element(By.ID, "experience_employer").send_keys("IBM")
        driver.find_element(By.ID, "experience_jobtitle").send_keys("s/w")
        save_btn = driver.find_element(By.XPATH, "//input[@value='Save']")
        save_btn.click()