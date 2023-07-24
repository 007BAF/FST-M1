import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
import time

@pytest.fixture(scope="class")
def setup():
    driver = webdriver.Firefox()
    driver.get("http://alchemy.hguy.co/orangehrm")
    driver.implicitly_wait(10)
    yield driver
    driver.quit()

@pytest.mark.usefixtures("setup")
class TestActivity8:

    @pytest.mark.priority(1)
    def test_login_to_system(self, setup):
        driver = setup
        driver.find_element(By.ID, "txtUsername").send_keys("orange")
        driver.find_element(By.ID, "txtPassword").send_keys("orangepassword123")
        driver.find_element(By.ID, "btnLogin").click()
        text = driver.find_element(By.ID, "welcome").text
        assert text == "Welcome orange"
        print(text)

    @pytest.mark.priority(2)
    def test_apply_leave(self, setup):
        driver = setup
        driver.find_element(By.XPATH, "//a//b[contains(text(),'Dashboard')]").click()
        actions = ActionChains(driver)
        apply_leave_link = driver.find_element(By.XPATH, "//a[@href='/orangehrm/symfony/web/index.php/leave/applyLeave']//span")
        actions.move_to_element(apply_leave_link).perform()
        apply_leave_link.click()
        leave_type_field = driver.find_element(By.ID, "applyleave_txtLeaveType")
        leave_type_field.send_keys("DayOff")
        leave_type_field.send_keys(Keys.TAB)
        driver.find_element(By.ID, "applyleave_txtFromDate").clear()
        driver.find_element(By.ID, "applyleave_txtFromDate").send_keys("2023-04-27")
        driver.find_element(By.ID, "applyleave_txtToDate").clear()
        driver.find_element(By.ID, "applyleave_txtToDate").send_keys("2023-04-28")
        driver.find_element(By.ID, "applyBtn").click()

        leave = driver.find_element(By.ID, "menu_leave_viewLeaveModule")
        actions.move_to_element(leave).perform()
        driver.find_element(By.ID, "menu_leave_viewMyLeaveList").click()

        status = driver.find_element(By.XPATH, "//a[contains(text(),'Pending Approval(7.00)')]")
        text = status.text
        assert text == "Pending Approval(7.00)"
