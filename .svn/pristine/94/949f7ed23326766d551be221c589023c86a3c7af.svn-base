// edit_init.js  初始化

var SUCCESS = 0;

var model = {};//当前页面中的数据模型

$(function(){
//	console.log('init()');
	//页面加载以后，立即加载笔记本列表
	loadNotebooksAction();//edit_notebook.js
	//绑定点击笔记本列表的事件
	//  showNoteAction 函数定义在 edit_note.js中
	$('#notebooks').on('click','li.notebook',showNotesAction);
	$('#notebooks').on('click','li.more',loadNotebooksAction);
	//绑定笔记列表点击事件
	//loadNoteAction 方法在 edit_note.js中定义
	$('#notes').on('click','li',loadNoteAction);
	//绑定保存笔记事件
	$('#save_note').click(saveNoteAction);
	//绑定添加笔记按钮事件
	$('#add_note').click(showAddNoteDialog);
	//绑定取消关闭
	$('#can').on('click','.close,.cancle',closeDialog);
	//绑定笔记子菜单
	$('#notes').on('click','.btn_slide_down',showNoteMenu);
	//绑定添加笔记本按钮事件
	$('#add_notebook').click(showAddNotebookDialog);
});

function showNoteMenu() {
//	console.log('showNoteMenu');
	//button->a->
//	$('#notes .note_menu').hide();
	$(this).parent().next().toggle();
	//绑定删除笔记按钮事件
	$('.btn_delete').click(deleteNote);
}

function closeDialog() {
	$('#can').empty();
	$('.opacity_bg').hide();
}































