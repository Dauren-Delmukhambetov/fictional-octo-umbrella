
---
# Data Masker serverless app

The serverless application that masks e-mail addresses in input text.

## Model

Request model:
```json
{"data":  "Hello Adam! I'm writing to you from john.locke@gmail.com" }
```
Response model:
```json
{"data":  "Hello Adam! I'm writing to you from masked@example.com" }
```
