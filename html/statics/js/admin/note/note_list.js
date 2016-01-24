/**
 * 
 */
var $table = $("#table");
var $form = $("#tableSearchForm");

$(function() {
	$table.bootstrapTable('removeAll');
	$table.bootstrapTable('refresh');
	initTable();
	
	$("#search").click(function () {
		
			$("#table").bootstrapTable('destroy');
			initTable();
		
	});
});


function initTable(){
	
	$('#table').commonbootstrapTable({
		url:"/web/note",
		queryParams: function(param) {
			var p = {
				pageSize: param.limit, //每页显示条数
				currentPage: param.offset / param.limit + 1 //第几页
			};
			var formData = $("#tableSearchForm").serializeJson(); //将查询表单参数转为json对象
			p = $.extend(p, formData);
			return p;
		},
		uniqueId: "id",
		columns: [{
			field: 'check',
			title: '全选',
			valign: 'middle',
			align: 'center',
			checkbox: true,
			width: '2px'
		}, {
			field: 'id',
			width: '5%',
			valign: 'middle',
			align: 'center',
			title: 'id'
		}, {
			field: 'title',
			width: '25%',
			valign: 'middle',
			align: 'center',
			title: '标题'
		}, {
			field: 'titlePic',
			width: '30%',
			valign: 'middle',
			align: 'center',
			title: '展示图片'
		}, {
			field: 'createTime',
			align: 'center',
			valign: 'middle',
			width: '15%',
			title: '发布时间',
			formatter: function(createTime) {
				var datetime = new Date(createTime);
				return datetime.format("yyyy-MM-dd HH:mm:ss");
			}
		}, {
			formatter: operateFormatter,
			align: 'center',
			valign: 'middle',
			width: '20%',
			title: '操作'
		}]
	});
}

function operateFormatter(value, row, index) {
	var html = '<a class="like" href="javascript:loadRoles(\'' + row.id + '\')" title="分配角色">分配角色</a>';
	return html;
}
