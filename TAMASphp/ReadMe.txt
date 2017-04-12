Note about using the web app:
- If accessing it directly, always start at Index.php, otherwise the dummy variables will not set correctly.
- The logout page redirects you to the login page after 3 seconds.
- All functionalities concerning instructors are implemented: post job, TA/Grader review, modify job allocation.
- Greyed out tabs/thumbnails/pages represent extra features that are still work-in-progress (e.g. profile).
- Login page provides the premade teacher dummy that can be used for manual testing
- No teacher registration has been provided as no further functionality can be performed on it afterwards. However, the controller fully implements that as reflected by our PHPunits.
- Refer to Dummies.php for a full list of dummies created.

Testing:
- One of the tests (testCreateReview(), line 684) has non-functional lines that have been commented out. This decision has been justified within the test in the instructorControllerTest.php file.
