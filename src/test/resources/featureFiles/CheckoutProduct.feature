Feature: Checkout Products


  #Background: The user is getting Logged in
    #Given The user is logged in with Phone_number


  Scenario Outline: Adding Product(s) in Cart
    Given The user gets navigated to "<Menu>" of "<Category>"
    When The user opens Quick view for "<Brand_name>" : "<Product>"
    And The user clicks Add_To_Bag and navigates to Cart page
    Then The user should be able to see "<Brand_name>" : "<Product>"
    Examples:
      | Category | Menu     | Brand_name | Product                         |
      | BEAUTY   | Perfumes | GUESS      | Bella Vita Rosa Eau De Toilette |