$(function() {
	$('#mainTable').datagrid( {
		title : 'Inbox',//表格标题  
		iconCls : 'icon-save',//表格图标  
		nowrap : false,//是否只显示一行，即文本过多是否省略部分。  
		striped : true,
		url : '../json/getInboxList.action', //action地址  
		sortName : 'mbId',
		sortOrder : 'asc',
		idField : 'mbId',
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'mbId',
			title : 'ID',
			width : 120, 
			sortable : true
		}, {
			field : 'fromName',
			title : 'From',
			width : 120
		}, {
			field : 'mbTitle',
			title : 'Title',
			width : 120
		}, {
			field : 'mbStatus',
			title : 'Status', 
			width : 120,
			formatter : statusFormatter
		}

		] ],
		rowStyler:function(index,row,css){
					if (row.mbStatus==0){
						return 'font-weight:bold;';
					}
				},
		pagination : true, //包含分页  
		singleSelect : true, 
		onDblClickRow:function(rowIndex){
					var row = $('#mainTable').datagrid('getSelected');
					$('#detailDialog').dialog('open');
					$('#editMBId').val(row.mbId);
					$('#editForm').submit();
    				$("#detailDialog").dialog('reload'); 
				}
	
	});
	
	
	$('#detailDialog').dialog({
		title : 'Content',
    	modal : true, 
    	closed : true
	}); 
	
	
	
	$('#editForm').form({  
    url:'../json/openMailbox.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		var json = $.parseJSON(resultObj);
    		var content = json.mbContent;
			var node = $("#div1")[0].childNodes[0];
			node.nodeValue = node.nodeValue.replace(/[^\n\s]+/g,function(match){
  			return RegExp.leftContext +  content + RegExp.rightContext;
			});
    		
    		
        	$('#mainTable').datagrid('reload');
    	}
    }  
	});
	
	
});




var status = [
		    {sbStatus:'0',statusDisplay:'Unread'},
		    {sbStatus:'1',statusDisplay:'Read'}
		];
function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].sbStatus == value) return status[i].statusDisplay;
			}
			return value;
};