// edit_notebook.js 
/*
 * 封装与笔记本有关的操作方法
 */
function loadNotebooksAction() {
	//获取page数据 $(this)是li
	var page = $(this).data('page');
	if(!page) {
		page = 0;
	}
	
//	console.log('loadNotebooksAction()');
	var url = "notebook/notebooks.do";
	var data = {userId:getCookie('userId'),page:page};
//	console.log(data);
//	console.log(url);
	$.getJSON(url,data,function(result){
		if(result.state == SUCCESS) {
			var list = result.data;
//			console.log(list);
			model.updateNotebooks(list,page);
		}
	});
}

/*
 * <li class="online">
	<a  class='checked'>
	<i class="fa fa-book" title="online" rel="tooltip-bottom">
	</i> 默认笔记本</a></li>
 */

model.updateNotebooks = function(list,page) {
	var template = '<li class="online notebook">'+
				'<a>'+
				'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
				'[notebook.name]</a></li>';
	if(!this.notebooks) {
		this.notebooks = list;
	} else {
		this.notebooks = this.notebooks.concat(list);
	}
	
//	console.log(this);
	
	var ul = $('#notebooks').empty();
	
	
	for(var i=0;i<this.notebooks.length;i++) {
		var notebook = this.notebooks[i];
		//id  name
		var li = template.replace('[notebook.name]',notebook.name);
		// 在DOM对象上绑定数据 index
		li = $(li).data('index',i);
		ul.append(li);	
	}
	var li = $('<li class="online more"><a>More...</a></li>');
//	console.log(li);
	li.data('page',++page);
	ul.append(li);
}

function showAddNotebookDialog() {
	$('#can').load('alert/alert_notebook.jsp',function(){
		$('#can .sure').click(addNotebookAction);
	});
	$('.opacity_bg').show();
}

function addNotebookAction() {
	var url = "notebook/add.do";
	var userId = getCookie('userId');
	var name = $('#input_notebook').val();
	var data = {userId:userId,name:name};
//	console.log(data);
	if(name.replace(" ","") == "") {
		return;
	}
	$.post(url,data,function(result){
		if(result.state == SUCCESS) {
//			console.log(result.data);
			model.updateNotebook(result.data);
			closeDialog();
		} else {
			alert(result.message);
		}
	});
}

model.updateNotebook = function(notebook) {
	this.notebooks.unshift({id:notebook.id,name:notebook.name});
	loadNotebooksAction();
}


























