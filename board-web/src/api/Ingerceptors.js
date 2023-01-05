import axios from "axios";
// import router from '@/assets/Router.js';

// Scheduler Interceptor
// 인스턴스를 생성하고, 인터셉터 설정 로직까지 완료된 후에 새로운 인스턴스를 리턴하는 로직을 하나의 함수로 만듦.
// createInstance()의 리턴 값(인스턴스)을 이용해서 api를 요청한다.
export function createInstance() {
	const instance = axios.create({
		headers: {
			"Content-Type": "multipart/form-data",
		},
	});
	return setInterceptors(instance);
}

// 인자로 axios instance를 전달받고, 인터셉터 설정 로직을 실행한 후에 인스턴스를 다시 리턴해주는 함수
function setInterceptors(instance) {
	instance.interceptors.request.use(
		function(config) {
			// 요청을 보내기 전에 실행
			return config;
		},
		function(error) {
			// 요청 에러난 경우 실행
			console.log('request error interceptor!!');
			return Promise.reject(error);
		},
	);

	instance.interceptors.response.use(
		function(response) {
			// 200대의 응답 데이터를 이용해 실행
			return response;
		},
		function(error) {
			// 200대 이외의 응답 에러난 경우 실행

			// 401 (Unauthorized): 사용자 인증 실패
            console.log('request error interceptor!!');
			return Promise.reject(error);
		},
	);

	return instance;
}