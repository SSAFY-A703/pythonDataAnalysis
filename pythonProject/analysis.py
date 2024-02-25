import csv
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/analyze', methods=['POST'])
def analyze_data():
    try:
        # CSV 데이터 받기
        csv_data = request.get_data(as_text=True)

        # CSV 데이터를 이용한 분석 로직 수행
        result = analyze_csv_data(csv_data)

        # 분석 결과를 스프링 백엔드로 전송
        return jsonify(result=result), 200, {'Content-Type': 'application/json; charset=utf-8'}
    except Exception as e:
        return jsonify(error=str(e)), 500, {'Content-Type': 'application/json; charset=utf-8'}

def analyze_csv_data(csv_data):
    try:
        # CSV 데이터 파싱 및 구조화
        rows = csv.DictReader(csv_data.splitlines())

        # 분석 결과를 리스트로 저장
        structured_data = [row for row in rows]

        # 분석 결과를 반환
        return {"analysis_result": "Structured CSV Data", "structured_data": structured_data}
    except Exception as e:
        # 오류 발생 시 처리
        raise Exception("Failed to analyze CSV data: " + str(e))

if __name__ == '__main__':
    app.config['JSON_AS_ASCII'] = False
    app.run(port=5000)