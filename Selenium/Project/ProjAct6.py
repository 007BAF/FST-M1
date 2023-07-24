import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.firefox import GeckoDriverManager


@pytest.fixture(scope="class")
def setup():
    driver = webdriver.Firefox(executable_path=GeckoDriverManager().install())
    driver.implicitly_wait(10)
    driver.get("http://alchemy.hguy.co/orangehrm")
    yield driver
    driver.quit()


@pytest.mark.usefixtures("setup")
class TestActivity4:
    def test_login(self, setup):
        username = setup.find_element(By.ID, "txtUsername")
        password = setup.find_element(By.ID, "txtPassword")
        loginbtn = setup.find_element(By.ID, "btnLogin")
        username.send_keys("orange")
        password.send_keys("orangepassword123")
        loginbtn.click()
        image = setup.find_element(By.XPATH, "//img[@alt='OrangeHRM']")
        assert "OrangeHRM" in image.get_attribute("alt")

    def test_add_employee(self, setup):
        menu_pim = setup.find_element(By.XPATH, "//a[@id='menu_pim_viewPimModule']")
        menu_pim.click()
        add_emp = setup.find_element(By.XPATH, "//a[@id='menu_pim_addEmployee']")
        add_emp.click()
        setup.find_element(By.ID, "firstName").send_keys("Ram")
        setup.find_element(By.ID, "lastName").send_keys("Sita")
        setup.find_element(By.ID, "employeeId").send_keys("111")
        setup.find_element(By.ID, "chkLogin").click()
        setup.find_element(By.ID, "btnSave").click()

    def test_directory(self,setup):
      menu_dir = setup.wait.until(EC.element_to_be_clickable((By.XPATH, "//a[@id='menu_directory_viewDirectory']")))
        expect = "SearchDirectory"
        if menu_dir.is_enabled():
            menu_dir.click()
            menu_dir.click()
            print("Is selected and clickable")

        search_dir = self.driver.find_element(By.XPATH, "//div[@class='head']//h1")
        print(search_dir.text)
        self.assertEqual(search_dir.text, expect)