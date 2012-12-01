$(function() {
	$('#mainTable').datagrid( {
		title : 'My Bids',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getSBidListByPageAndSupplier.action', //action地址  
		sortName : 'mId',
		sortOrder : 'desc',
		idField : 'mId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'sbId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'moDisplay',
			title : 'Detail',
			width : 120
		}, {
			field : 'sbBid',
			title : 'Price',
			width : 120
		}, {
			field : 'sbStatus',
			title : 'Status',
			width : 120
		},

		] ],
		pagination : true, //包含分页  
		singleSelect : true,
		toolbar : [ {
			text : 'Delete',
			iconCls : 'icon-cancel',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected && selected.sbStatus==0){
					$.messager.confirm('Confirm', 'Are you confirm?', function(r){
						if (r){
							$('#delSBId').val(selected.sbId);
							$('#delForm').submit(); 
						}
					});
				}
				
			}
		} ]
	});
	
	
	$('#delForm').form({  
    url:'../json/delBid.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'A bid be deleted!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
});
