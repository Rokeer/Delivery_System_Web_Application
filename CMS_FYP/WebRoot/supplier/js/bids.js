$(function() {
	$('#mainTable').datagrid( {
		title : 'Bids',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getMOrdersListWithoutSelection.action', //action地址  
		sortName : 'moId',
		sortOrder : 'desc',
		idField : 'moId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'moId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'materialName',
			title : 'Material',
			width : 120
		}, {
			field : 'moAmount',
			title : 'Amount',
			width : 120
		}

		] ],
		pagination : true, //包含分页  
		singleSelect : true, 
		onDblClickRow:function(rowIndex){
					var row = $('#mainTable').datagrid('getSelected');
					var content = row.materialName+"("+row.moAmount+")";
					$('#BidDialog').dialog('open');
					$('#addMOId').val(row.moId);
    				var node = $("#MDetail")[0].childNodes[0];
					node.nodeValue = node.nodeValue.replace(/[^\n\s]+/g,function(match){
  						return RegExp.leftContext + content + RegExp.rightContext;
					});
    				
				}
	
	});
	
	
	$('#BidDialog').dialog({
		title : 'Bid Detail',
    	modal : true, 
    	closed : true
	}); 
	
	
	
	
	$('#addForm').form({  
    url:'../json/addBid.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
        	$.messager.show({
				title:'Success!',
				msg:'Success!',
				timeout:4000,
				showType:'slide'
			});
        	$('#BidDialog').dialog('close');
        	$('#addForm').form('clear');
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	
});


function submitAddForm(){
	if(isNaN(document.addForm.addSBPrice.value)||document.addForm.addSBPrice.value<=0){
   		alert('Price must be a positive number!')
   		document.addForm.addSBPrice.focus();
   		return false;
	} 
	$('#addForm').submit();  	
};