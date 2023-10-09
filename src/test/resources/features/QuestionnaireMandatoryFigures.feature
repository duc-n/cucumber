@CMAUT-5501 @Automated_Tests
#noinspection CucumberUndefinedStep
Feature: Mandatory figures

  Leave mandatory figures empty and check that the Quote button, in Pricing, is shaded
  Fill the mandatory figures and check that the Quote button, in Pricing, is clickable

  Background:
    Given User is on the homepage
    When In the Search Criteria panel, user clicks on the Search button after filling the following data :
      | Owner name       | Company Name      |
      | Duc Thang NGUYEN | AIRBUS OPERATIONS |
    And In the Search results panel, user clicks on the first result

  @CMAUT-5287 @CMAUT-5291 @Automated_Tests @QuestionnaireTest
  Scenario Outline: SC02 - NB_Leave Mandatory figures empty and check that the Quote button is shaded
    Given User is on the Questionnaire tab of the contract number that is created in #CMAUT-5028
    #Given User is on Autonomy Contract Home Page from Elan application
    When User clicks on the MANDATORY FIGURES Accordion and fills the fields "<numberCustomer>" "<domesticTurnover>" "<exportTurnover>" "<theoriticalDSO>"
    And User clicks on the ANALYSIS OF BAD DEBTS EXPERIENCE accordion and sets the badDebtsExperience to No
    And On the Contract Terms page User clicks on the Pricing accordion
    Then the Quote button is "<quoteButtonEnable>"
		#True : the Quote button is greyed
		#False: the Quote button is clickable

    Examples:
      | numberCustomer | domesticTurnover | exportTurnover | theoriticalDSO | quoteButtonEnable |
      |                |                  |                |                | false             |
      | 58             | 34700000         | 1560000        | 60             | true              |