Ext.define('Admin.view.profile.FileUploadForm', {
    extend: 'Ext.form.Panel',
    alias:'widget.fileUploadForm',
    //layout: 'column',
    items:[
        {
            xtype: 'box',
            width: 300,
            maxWidth: 300,
            autoEl:{
                tag: 'img'
            }
        },
        {
            xtype: 'component',
            html: ''
        },
        {
            xtype: 'hiddenfield',
            name: 'userId',
            value: window.userIdTmp
        }
    ],
    buttons: [
        {
            xtype:'filefield',
            buttonOnly:true,
            buttonText: '选择图片',
            name: 'photo',
            listeners:{
                change:function(fileFiled,value,eOpts){
                    var me = this;
                    var image = me.up('panel').down('box').getEl().dom;
                    var file = fileFiled.fileInputEl.dom.files.item(0);
                    var fileReader = new FileReader(value);
                    fileReader.readAsDataURL(file);
                    fileReader.onload=function(e){
                        window.uploadImag = e.target.result;
                        image.src = e.target.result;
                    };
                }
            }
        },'->',
        {
            text: '确定',
            handler: function(btn){
                var photoForm = btn.up('panel');
                if(photoForm.getForm().getFields().items[1].lastValue){
                    photoForm.submit({
                            url: 'staff/updatePicture',
                            method: 'POST',
                            success: function(form, result){
                                var win = btn.up('window');
                                    var image = Ext.getCmp('socialCard').down('image').getEl().dom;
                                    image.src = window.uploadImag;
                                    window.uploadImag = null;
                                    Ext.Msg.alert('Success', '上传成功');
                                if(win){
                                    win.close();
                                }
                            }
                        }
                    );
                }else{
                    Ext.Msg.alert('Error', '未选择图片');
                }
            }
        },{
            text: '取消',
            handler:function(btn) {
                var win = btn.up('window');
                if(win){
                   window.uploadImag = null;
                   win.close();
                }
            }
        }]
});