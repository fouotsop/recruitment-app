import requests
import json

test_data = {
    "candidates": [
        {
            "id": 1,
            "education": 4,
            "skills": 7,
            "experience": 3,
            "ahp_score": 0.82,
            "salary": 420000,
            "gender": "female",
            "region": "Littoral"
        },
        {
            "id": 2,
            "education": 5,
            "skills": 8,
            "experience": 5,
            "ahp_score": 0.91,
            "salary": 500000,
            "gender": "male",
            "region": "Centre"
        }
    ],
    "num_to_select": 1,
    "min_education": 4
}

response = requests.post('http://localhost:5000/optimize', json=test_data)

print("Status Code:", response.status_code)
print("Response:")
print(json.dumps(response.json(), indent=2))