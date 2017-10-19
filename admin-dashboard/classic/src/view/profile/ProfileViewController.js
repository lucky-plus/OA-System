Ext.define('Admin.view.profile.ProfileViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.profileViewController',

	showFileUploadFormWindow:function(){
	        Ext.widget('fileUploadFormWindow',{
				title:'上传图片',
				items: [{xtype: 'fileUploadForm'}]
			});
	    },

    saveInfomationSubmit: function(btn) {
	var profileForm = btn.up('form').getForm();
		//this.lookupReference('assetsGrid').store.reload();  //lookupReference配合reference属性
		profileForm.submit( {
			//url : '',
			method : 'post',
			success : function(form, action) {
				Ext.Msg.alert("提示",action.result.msg);
				win.close();
				Ext.getCmp("assetsGrid").store.reload();
			},
			failure : function(form, action) {
				Ext.Msg.alert("提示",action.result.msg);

			}
		});
    },

	imageFileUpload: function(btn) {
	var fileUploadForm = btn.up('form').getForm();
	var win = btn.up('window');
	fileUploadForm.submit({
			url : 'resources/upload',
			method : 'post',
			enctype:'multipart/form-dat',
			success : function(form, action) {
				Ext.Msg.alert("提示",action.result.msg);
				win.close();
				Ext.getCmp('resourcesGrid').store.reload();
			},
			failure : function(form, action) {
				Ext.Msg.alert("提示",action.result.msg);
			}
	   })
    },

	profileGridWindowClose: function(btn) {
		var win = btn.up('window');
		if(win){
			win.close();
		}
    }
});
