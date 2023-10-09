@IA
Feature: Create a new business case

  @CMAUT-5028 @CMAUT-5291 @Automated_Tests
  Scenario: SC01 - NB_Contract Creation
    Given User is on Autonomy Contract Home Page from Elan application
    When User creates a new business case with the below easyEstabNumber
      | easyEstabNumber | 0000008160832400001 |

    Then The application navigates the questionnaire page. A new contract is created and the company name is "AIRBUS OPERATIONS"
