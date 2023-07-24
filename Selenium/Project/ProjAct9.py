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
    driver.quit()

@pytest.mark.usefixtures("setup")
class TestActivity9:

    @pytest.mark.priority(1)
    def test_login_to_system(self, setup):
        driver, wait = setup
        driver.find_element(By.ID, "txtUsername").send_keys("orange")
        driver.find_element(By.ID, "txtPassword").send_keys("orangepassword123")
        driver.find_element(By.ID, "btnLogin").click()
        text = driver.find_element(By.ID, "welcome").text
        assert text == "Welcome orange"
        print(text)

    @pytest.mark.priority(2)
    def test_retrieve_emergency_contacts(self, setup):
        driver, wait = setup
        driver.find_element(By.ID, "menu_pim_viewMyDetails").click()
        driver.find_element(By.ID, "menu_pim_viewMyDetails").click()
        driver.find_element(By.XPATH, "//a[contains(text(),'Emergency Contacts')]").click()
        table = driver.find_element(By.ID, "emgcontact_list")
        all_rows = table.find_elements(By.TAG_NAME, "tr")
        for row in all_rows:
            cells = row.find_elements(By.TAG_NAME, "td")
            for cell in cells:
                print("Content from the Table - ", cell.text)
                print("\t")
            print()