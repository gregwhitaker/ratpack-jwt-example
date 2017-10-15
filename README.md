# ratpack-jwt-example
[![Build Status](https://travis-ci.org/gregwhitaker/ratpack-jwt-example.svg?branch=master)](https://travis-ci.org/gregwhitaker/ratpack-jwt-example)

An example of using JSON Web tokens to authenticate with [Ratpack](http://www.ratpack.io).

This example starts a Ratpack application that hosts two endpoints: one secured with JWT, the other not.

## Prerequisites
This example requires that you have installed the [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html).

## Running the Example
1. Generate a `READ_WRITE` role JWT token by running the following Gradle command:

        $ ./gradlew generateReadWrite
    
2. Copy the generated token to the clipboard (It will look similar to the one below):

        eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..rYmzSzVwurf_tJRk.VL_IzEqi9bihjSd44W8HCTIm2tfVqMTCAHklSxbsteO67mZ0nqxXfeo7h7D0tWy0Wk4Hg7jJngBu5Thd2m5S27xUlS9YsakkROmIOkdnsCs5j4mv5Qd9PgPRxSWAH2qC_K7fywpGlUoPojGO03MA0odbR9PPS4WpuBlealgKF2gAbcfADokyNy897AehcmEJ6ZRwzP--vKcCP59Z1CSqHEEgwLGgXOOQl7PAMgvhOHJwJJl5OvhHcQRg9vG89CegONXFypZNWDGv0LtcCjJ-H8_ZH-wWtkqjXyC2m9QipORc_Of-uLov-kJeuIRkT-M3DqwIyqofaO7MDv7xAuCtk_jpl0uN.UNev8gOwcbEhr9fJOCA5BQ

3. Start the example service by running the following Gradle command:

        $ ./gradlew run

4. Curl the JWT protected endpoint using the following command:

        $ curl -v http://localhost:5050/secure
        
5. Notice that you receive a `401 - Unauthorized` error.

6. Curl the JWT protected endpoint and supply the JWT token with the following command:

        $ curl -v http://localhost:5050/secure?token={your token}

7. Notice that you now have access to the secure resource and your user information.

        {
          "message": "This endpoint is protected by JWT",
          "user": {
            "displayName": "jdoe",
            "roles": [
              "READ_WRITE"
            ],
            "email": "john.doe@netifi.com"
          }
        }

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/ratpack-jwt-example/issues).

## License
MIT License

Copyright (c) 2017 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
