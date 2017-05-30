<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-lg-12">
		<br />
		<h1 class="page-header">Create new Test</h1>
	</div>

</div>
<div>
	<div class="col-lg-12">
		<form enctype="multipart/form-data" method="post" id="upload">
			<div class="form-group">
				<label>Name</label>
				<input class="form-control" name="testName" required />
			</div>
			<div class="form-group">
				<label>Please import content in the following format:</label>
				<a href="${pageContext.request.contextPath}/resources/template/template_test.xlsx"
					target="_blank"><code>templete_test.xlsx</code></a><br />
				<label>Upload content of the exam:</label>
				<input class="form-control" name="excelfile" type="file" accept=".xlsx" required />
			</div>
			<br />
			<div class="form-group">
				<label>Upload image and audio in part 1 (file name: 1.png, 1.mp3, 2.png, 2.mp3, ..., 10.png, 10.mp3):</label>
				<input class="form-control" name="part1" id="part1" multiple type="file" onchange="makeFileListPart1();" accept=".png, .mp3" required />
				<label id="fileListPart1"></label>
			</div>
			<br />
			<div class="form-group">
				<label>Upload audio in part 2 (file name: 1.mp3, 2.mp3, ..., 30.mp3):</label>
				<input class="form-control" name="part2" id="part2" onchange="makeFileListPart2();" accept=".mp3"
					multiple type="file" required />
				<label id="fileListPart2"></label>
			</div>
			<br />
			<div class="form-group">
				<label>Upload audio in part 3 (file name: 1.mp3, 2.mp3, ..., 10.mp3):</label>
				<input class="form-control" name="part3" id="part3" onchange= "makeFileListPart3();" accept=".mp3"
					multiple type="file" required />
				<label id="fileListPart3"></label>
			</div>
			<br />
			<div class="form-group">
				<label>Upload audio in part 4 (file name: 1.mp3, 2.mp3, ..., 10.mp3):</label>
				<input class="form-control" name="part4" id="part4" onchange="makeFileListPart4();" accept=".mp3"
					multiple type="file" required />
				<label id="fileListPart4"></label>
			</div>
			<br />
			<div class="form-group">
				<label>Upload image in part 7 (file name: 1.png, 2.png, ..., 10.png):</label>
				<input class="form-control" name="part7" id="part7" onchange="makeFileListPart7();" accept=".png"
					multiple type="file" required />
				<label id="fileListPart7"></label>
			</div>
			<div class="form-group">
				<label>Level:</label><br/>
				<input type="radio" name="level" value="Easy" checked> Easy
  				<input type="radio" name="level" value="Medium"> Medium
  				<input type="radio" name="level" value="Hard"> Hard
			</div>
			<br />
			<button type="submit" class="btn btn-primary">Create new new Test</button>
			<br />
		</form>

<script type="text/javascript">
	function makeFileListPart1() {
		var input = document.getElementById("part1");
		var filelistPart = document.getElementById("fileListPart1");
		filelistPart.innerText = input.files[0].name;
		for (var i = 1; i < input.files.length; i++) {
			filelistPart.innerText = filelistPart.innerText + ", " + input.files[i].name
		}
		filelistPart.innerText=filelistPart.innerText+" was selected"
	}
	function makeFileListPart2() {
		var input = document.getElementById("part2");
		var filelistPart = document.getElementById("fileListPart2");
		filelistPart.innerText = input.files[0].name;
		for (var i = 1; i < input.files.length; i++) {
			filelistPart.innerText = filelistPart.innerText + ", " + input.files[i].name
		}
		filelistPart.innerText=filelistPart.innerText+" was selected"
	}
	function makeFileListPart3() {
		var input = document.getElementById("part3");
		var filelistPart = document.getElementById("fileListPart3");
		filelistPart.innerText = input.files[0].name;
		for (var i = 1; i < input.files.length; i++) {
			filelistPart.innerText = filelistPart.innerText + ", " + input.files[i].name
		}
		filelistPart.innerText=filelistPart.innerText+" was selected"
	}
	function makeFileListPart4() {
		var input = document.getElementById("part4");
		var filelistPart = document.getElementById("fileListPart4");
		filelistPart.innerText = input.files[0].name;
		for (var i = 1; i < input.files.length; i++) {
			filelistPart.innerText = filelistPart.innerText + ", " + input.files[i].name
		}
		filelistPart.innerText=filelistPart.innerText+" was selected"
	}
	function makeFileListPart7() {
		var input = document.getElementById("part7");
		var filelistPart = document.getElementById("fileListPart7");
		filelistPart.innerText = input.files[0].name;
		for (var i = 1; i < input.files.length; i++) {
			filelistPart.innerText = filelistPart.innerText + ", " + input.files[i].name
		}
		filelistPart.innerText=filelistPart.innerText+" was selected"
	}
</script>
	</div>
</div>