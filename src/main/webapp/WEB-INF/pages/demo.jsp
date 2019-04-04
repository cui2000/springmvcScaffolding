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
		var vue_actor = new Vue({
			el : "#div_actor",
			data : {
				pageEntity : {},
				loaded:false
			},
			methods:{
				refreshActorData:function (page) {
					vue_actor.loaded=false;
					axios.get('${pageContext.request.contextPath}/getActorData', {
					    params: {
					      page: page||1
					    }
					  })
					  .then(function (response) {
					    vue_actor.pageEntity = response.data;
						vue_actor.pageEntity.totalPage = response.data.totalCount
								% response.data.rowSize == 0 ? response.data.totalCount
								/ response.data.rowSize
								: ((response.data.totalCount / response.data.rowSize) + 1);
						vue_actor.loaded=true;
					  })
					  .catch(function (error) {
						  alert("异常，请联系管理员.")
					  });
				}
			}
		});
		
		vue_actor.refreshActorData();
	});
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
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshActorData(1)" class="page-link" href="javascript:void(0)">&laquo;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == 1}"><a v-on:click="refreshActorData(pageEntity.currentPage-1)" class="page-link" href="javascript:void(0)">&lt;</a></li>
						<li v-for="(page,index) in pageEntity.totalPage" class="page-item" :class="{active:page == pageEntity.currentPage}"><span v-if="page == pageEntity.currentPage" class="page-link">
								{{page}} <span class="sr-only">(current)</span>
						</span> <a v-else v-on:click="refreshActorData(page)" class="page-link" href="javascript:void(0)">{{page}}</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshActorData(pageEntity.currentPage+1)" class="page-link" href="javascript:void(0)">&gt;</a></li>
						<li class="page-item" :class="{disabled:pageEntity.currentPage == pageEntity.totalPage}"><a v-on:click="refreshActorData(pageEntity.totalPage)" class="page-link" href="javascript:void(0)">&raquo;</a></li>
					</ul>
					</nav>
				</div>
			</div>
			<div class="col">One of three columns</div>
			<div class="col">One of three columns</div>
		</div>
	</div>
</body>
</html>