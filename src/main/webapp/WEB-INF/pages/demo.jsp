<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="http://47.75.250.166/resource/bootstrap-4.3.1-dist/css/bootstrap.min.css">

<script src="http://47.75.250.166/resource/js/jquery-3.3.1.min.js"></script>
<script src="http://47.75.250.166/resource/js/popper-1.14.7.min.js"></script>
<script src="http://47.75.250.166/resource/js/vue-2.6.10.js"></script>
<script src="http://47.75.250.166/resource/js/moment-2.10.6.min.js"></script>
<script src="http://47.75.250.166/resource/js/axios-0.18.0.min.js"></script>
<title>hello world</title>
<script type="text/javascript">
	$(function() {
		function createVue(id,url){
			var vue = new Vue({
				el : id,
				data : {
					pageEntity : {},
					loaded:false
				},
				methods:{
					refreshData:function (page) {
						vue.loaded=false;
						axios.get(url, {
						    params: {
						      page: page||1
						    }
						  })
						  .then(function (response) {
							  vue.pageEntity = response.data;
							  vue.pageEntity.totalPage = response.data.totalCount
								% response.data.rowSize == 0 ? response.data.totalCount
										/ response.data.rowSize
										: (parseInt(response.data.totalCount / response.data.rowSize) + 1);
							  vue.loaded=true;
						  })
						  .catch(function (error) {
							  alert("异常，请联系管理员.")
						  });
					}
				}
			});
			return vue;
		}
		
		// 演员
		var vue_actor = createVue('#div_actor','${pageContext.request.contextPath}/getActorData');
		vue_actor.refreshData();
		
		// 客户
		var vue_customer = createVue('#div_customer','${pageContext.request.contextPath}/getCustomerData');
		vue_customer.refreshData();
		
		// 电影
		var vue_film = createVue('#div_film','${pageContext.request.contextPath}/getFilmData');
		vue_film.refreshData();
		
		
		
		
	});
	
	/*
	function test(key) {
		return axios.get('${pageContext.request.contextPath}/test?key='+key);
	}

	function requests(){
		var arr = [];
		for(var i = 0;i<40000;i++){
			arr.push(test(random()));
		}
		return arr;
	}
	
	function random(){
	    return Math.floor(Math.random()*10);
	}
	
	function sendTest(){
		var d = new Date();
		axios.all(requests())
			.then(axios.spread(function (acct, perms) {
			// 两个请求现在都执行完成
				console.log("acct");
				console.log(acct);
				console.log("perms");
				console.log(perms);
				console.log(new Date().getTime()-d.getTime())
		}));
	}
	*/
	
	function testRent(){
		axios.get('${pageContext.request.contextPath}/testRent').then(function(res){
			console.log(res);
		});
	}
	
	function testRent_2(){
		axios.all([testRent(),testRent()])
		.then(axios.spread(function (acct, perms) {
		// 两个请求现在都执行完成
			console.log("acct");
			console.log(acct);
			console.log("perms");
			console.log(perms);
		}));
	}
	function test() {
		axios.get('http://localhost:8108/demo/getActorData').then(function(res){
			console.log(res);
		});
	}
	function rent() {
		axios.get('${pageContext.request.contextPath}/rent').then(function(res){
			console.log(res);
		});
	}
	function autoRent() {
		axios.get('${pageContext.request.contextPath}/autoRent').then(function(res){
			console.log(res);
		});
	}
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="#">Navbar</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Dropdown </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	</nav>
	<div>
		<button onclick="testRent()">testRent</button>
		<button onclick="testRent_2()">testRent_2</button>
		<button onclick="test()">test</button>
		<button onclick="rent()">rent</button>
		<button onclick="autoRent()">autoRent</button>
		<!-- <button onclick="sendTestResult()">sendTestResult</button>
		<button onclick="sendTestReset()">sendTestReset</button> -->
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col" id="div_actor">
				<h1>Actor Data</h1>
				<div v-if="!loaded" class="spinner-border" role="status">
					<span class="sr-only">Loading...</span>
				</div>
				<div v-if="loaded">
					<table class="table table-hover table-sm table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col">#</th>
								<th scope="col">FirstName</th>
								<th scope="col">LastName</th>
								<th scope="col">lastUpdate</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(actor,index) in pageEntity.datas">
								<th scope="row">{{(pageEntity.currentPage-1)*pageEntity.rowSize+index+1}}</th>
								<td>{{actor.firstName }}</td>
								<td>{{actor.lastName }}</td>
								<td>{{moment(actor.lastUpdate).format('YYYY-MM-DD HH:mm:ss') }}</td>
							</tr>
						</tbody>
					</table>
					<nav>
					<ul class="pagination justify-content-center">
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshctorData(1)" class="page-link" href="javascript:void(0)">&laquo;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshData(pageEntity.currentPage-1)" class="page-link" href="javascript:void(0)">&lt;</a></li>
						<li v-for="(page,index) in pageEntity.totalPage" class="page-item" :class="{active:page == pageEntity.currentPage}"><span v-if="page == pageEntity.currentPage" class="page-link">
								{{page}} <span class="sr-only">(current)</span>
						</span> <a v-else v-on:click="refreshData(page)" class="page-link" href="javascript:void(0)">{{page}}</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.currentPage+1)" class="page-link" href="javascript:void(0)">&gt;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.totalPage)" class="page-link" href="javascript:void(0)">&raquo;</a></li>
					</ul>
					</nav>
				</div>
			</div>
			<div id="div_customer" class="col">
				<h1>Customer Data</h1>
				<div v-if="!loaded" class="spinner-border" role="status">
					<span class="sr-only">Loading...</span>
				</div>
				<div v-if="loaded">
					<table class="table table-hover table-sm table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col">#</th>
								<th scope="col">FirstName</th>
								<th scope="col">LastName</th>
								<th scope="col">lastUpdate</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(customer,index) in pageEntity.datas">
								<th scope="row">{{(pageEntity.currentPage-1)*pageEntity.rowSize+index+1}}</th>
								<td>{{customer.firstName }}</td>
								<td>{{customer.lastName }}</td>
								<td>{{moment(customer.lastUpdate).format('YYYY-MM-DD HH:mm:ss') }}</td>
							</tr>
						</tbody>
					</table>
					<nav>
					<ul class="pagination justify-content-center">
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshData(1)" class="page-link" href="javascript:void(0)">&laquo;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshData(pageEntity.currentPage-1)" class="page-link" href="javascript:void(0)">&lt;</a></li>
						<li v-for="(page,index) in pageEntity.totalPage" class="page-item" :class="{active:page == pageEntity.currentPage}"><span v-if="page == pageEntity.currentPage" class="page-link">
								{{page}} <span class="sr-only">(current)</span>
						</span> <a v-else v-on:click="refreshData(page)" class="page-link" href="javascript:void(0)">{{page}}</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.currentPage+1)" class="page-link" href="javascript:void(0)">&gt;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.totalPage)" class="page-link" href="javascript:void(0)">&raquo;</a></li>
					</ul>
					</nav>
				</div>
			</div>
			<div id="div_film" class="col">
				<h1>Film Data</h1>
				<div v-if="!loaded" class="spinner-border" role="status">
					<span class="sr-only">Loading...</span>
				</div>
				<div v-if="loaded">
					<table class="table table-hover table-sm table-striped">
						<thead class="thead-dark">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Title</th>
								<th scope="col">Description</th>
								<th scope="col">lastUpdate</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(film,index) in pageEntity.datas">
								<th scope="row">{{(pageEntity.currentPage-1)*pageEntity.rowSize+index+1}}</th>
								<td>{{film.title }}</td>
								<td>{{film.description }}</td>
								<td>{{moment(film.lastUpdate).format('YYYY-MM-DD HH:mm:ss') }}</td>
							</tr>
						</tbody>
					</table>
					<nav>
					<ul class="pagination justify-content-center">
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshData(1)" class="page-link" href="javascript:void(0)">&laquo;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshData(pageEntity.currentPage-1)" class="page-link" href="javascript:void(0)">&lt;</a></li>
						<li v-for="(page,index) in pageEntity.totalPage" class="page-item" :class="{active:page == pageEntity.currentPage}"><span v-if="page == pageEntity.currentPage" class="page-link">
								{{page}} <span class="sr-only">(current)</span>
						</span> <a v-else v-on:click="refreshData(page)" class="page-link" href="javascript:void(0)">{{page}}</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.currentPage+1)" class="page-link" href="javascript:void(0)">&gt;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshData(pageEntity.totalPage)" class="page-link" href="javascript:void(0)">&raquo;</a></li>
					</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</body>
</html>