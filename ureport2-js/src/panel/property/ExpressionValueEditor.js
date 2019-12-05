/**
 * Created by Jacky.Gao on 2017-02-07.
 */
import CodeMirror from 'codemirror';
import '../../../node_modules/codemirror/addon/hint/show-hint.js';
import '../../../node_modules/codemirror/addon/lint/lint.js';
import {setDirty} from '../../Utils.js';
import BaseValueEditor from './BaseValueEditor.js';

export default class ExpressionValueEditor extends BaseValueEditor{
    constructor(parentContainer,context){
        super();
        this.context=context;
        this.container=$(`<div></div>`);
        parentContainer.append(this.container);
        this._buildWrapCompute(this.container);
        this._buildExpand();
        this._buildLineHeight();
        this._buildFormat();
        this._buildConditionProperty();
        this._initCodeEditor();
    }
    _initCodeEditor(){
        this.container.append(`<label>${window.i18n.property.expr.expr}</label>`);
        const editorContainer=$(`<div style="border: solid 1px #eeeeee;"></div>`);
        this.container.append(editorContainer);
        const codeEditor=$(`<textarea></textarea>`);
        editorContainer.append(codeEditor);
        const _this=this;
        setTimeout(function(){
            _this.codeMirror=CodeMirror.fromTextArea(codeEditor.get(0),{
                mode:'javascript',
                lineNumbers:true,
                gutters: ["CodeMirror-linenumbers", "CodeMirror-lint-markers"],
                lint: {
                    getAnnotations:_this._buildScriptLintFunction(),
                    async:true
                }
            });
            _this.codeMirror.setSize('auto','160px');
            _this.codeMirror.on('change',function(cm,changes){
                let expr=cm.getValue();
                _this.cellDef.value.value=expr;
                _this.context.hot.setDataAtCell(_this.rowIndex,_this.colIndex,expr);
                setDirty();
            });
            _this.container.hide();
        },100);
    }
    _buildExpand(){
        const _this=this;
        const expandGroup=$(`<div class="form-group" style="margin-bottom: 10px;"><label>${window.i18n.property.expr.expand}</label></div>`);
        this.downExpandRadio=$(`<label class="checkbox-inline" style="padding-left: 2px"><input type="radio" name="__expand_radio" value="Down">${window.i18n.property.expr.down}</label>`);
        expandGroup.append(this.downExpandRadio);
        this.downExpandRadio.children('input').click(function(){
            _this._setExpand('Down');
        });
        this.rightExpandRadio=$(`<label class="checkbox-inline" style="padding-left: 2px"><input type="radio" name="__expand_radio" value="Right">${window.i18n.property.expr.right}</label>`);
        expandGroup.append(this.rightExpandRadio);
        this.rightExpandRadio.children('input').click(function(){
            _this._setExpand('Right');
        });
        this.noneExpandRadio=$(`<label class="checkbox-inline" style="padding-left: 2px"><input type="radio" name="__expand_radio" value="None">${window.i18n.property.expr.noneExpand}</label>`);
        expandGroup.append(this.noneExpandRadio);
        this.noneExpandRadio.children('input').click(function(){
            _this._setExpand('None');
        });
        this.container.append(expandGroup);
    }

    _setExpand(expand){
        const hot=this.context.hot;
        for(let i=this.rowIndex;i<=this.row2Index;i++){
            for(let j=this.colIndex;j<=this.col2Index;j++){
                const cellDef=hot.context.getCell(i,j);
                if(!cellDef){
                    continue;
                }
                const type=cellDef.value.type;
                if(type==='dataset' || type==='expression'){
                    cellDef.expand=expand;
                }
            }
        }
        hot.render();
        setDirty();
    }

    _buildLineHeight(){
        const _this=this;
        const group=$(`<div class="form-group" style="margin-left: 8px;margin-top: 5px;margin-bottom: 5px;"><label>${window.i18n.property.expr.lineHeight}</label></div>`);
        this.lineHeightEditor=$(`<input type="number" class="form-control" placeholder="${window.i18n.property.expr.lineHeightTip}" style="display: inline-block;width: 316px;padding: 3px;font-size: 12px;height: 25px;">`);
        group.append(this.lineHeightEditor);
        this.lineHeightEditor.change(function(){
            const value=$(this).val();
            _this.cellDef.cellStyle.lineHeight=value;
            let td=_this.context.hot.getCell(_this.rowIndex,_this.colIndex);
            if(value===''){
                $(td).css("line-height",'');
            }else{
                $(td).css("line-height",value);
            }
            _this.context.hot.render();
        });
        this.container.append(group)
    }




    show(cellDef,rowIndex,colIndex,row2Index,col2Index){
        this.cellDef=cellDef;
        this.datasources=this.context.reportDef.datasources;
        this.rowIndex=rowIndex;
        this.colIndex=colIndex;
        this.row2Index=row2Index;
        this.col2Index=col2Index;
        this.container.show();
        this.codeMirror.setValue(cellDef.value.value);
        const expand=cellDef.expand;
        if(expand==='None'){
            this.noneExpandRadio.trigger('click');
        }else if(expand==='Down'){
            this.downExpandRadio.trigger('click');
        }else if(expand==='Right'){
            this.rightExpandRadio.trigger('click');
        }
        const cellStyle=cellDef.cellStyle;
        if(cellStyle.format){
            this.formatEditor.val(cellStyle.format);
        }else{
            this.formatEditor.val('');
        }
        if(cellStyle.wrapCompute){
            this.enableWrapComput.children('input').prop('checked',true);
        }else{
            this.disableWrapComput.children('input').prop('checked',true);
        }
    }
    hide(){
        this.container.hide();
    }
}