$(function() {
	$('#mainTable').datagrid( {
		title : 'Products',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getProductsList.action', //action地址  
		sortName : 'pId',
		sortOrder : 'desc',
		idField : 'pId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'pId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'pName',
			title : 'Name',
			width : 120
		}, {
			field : 'pAmount',
			title : 'Amount',
			width : 120
		}, {
			field : 'displayMaterials', 
			title : 'Materials',
			width : 120
		}, {
			field : 'makeAmount',
			title : 'Amount can be made', 
			width : 120
		}, {
			field : 'total',
			title : 'Total', 
			width : 120
		}

		] ],
		pagination : true, //包含分页  
		singleSelect : true,
		toolbar : [ {
			text : 'Delete',
			iconCls : 'icon-cancel',
			handler : function() {
				var selected = $('#mainTable').datagrid('getSelected');
				if (selected){
					$.messager.confirm('Confirm', 'Are you confirm to delete '+selected.pName+'?', function(r){
						if (r){
							$('#delPId').val(selected.pId);
							$('#delForm').submit(); 
						}
					});
				}
			}
		} ]
	});
	
	
	
	
	
	
	
	$('#delForm').form({  
    url:'../json/delProduct.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'A kind of product be deleted!',
				timeout:4000,
				showType:'slide'
			});
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
});


