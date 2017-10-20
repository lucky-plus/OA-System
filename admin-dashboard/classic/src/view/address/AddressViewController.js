Ext.define('Admin.view.address.AddressViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.addressViewController',

    addressGridSearch: function(bt) {
		var searchField = this.lookupReference('addressGridSearchField').getRawValue();
		var searchText = this.lookupReference('addressGridSearchText').getValue();
		Ext.Ajax.request({ 
			url : 'staff/findByPage', 
			params : { 
                    realName:searchText,
					deptName:searchField,
					page:1,
					start:0,
					limit:25,
					sort:'userId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				Ext.getCmp('addressGrid').getStore().loadData(tnpdata.content,false);
			}
			
	   });
	},

	messageDownload: function(){
     Ext.Msg.confirm("提示", "确定导出通讯录吗？", function (button) {
      if (button == "yes") {
         var file="/OA-System/download";
         location.href=file;
      }
     });
   }

});