<template>
  <div>
    <quill-editor class="editor"
                  v-loading="uploadLoading"
                  v-model="content"
                  ref="quillEditor"
                  :style="style"
                  :options="editorOption"
                  @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
                  @change="onEditorChange($event)">
    </quill-editor>
    <!-- 图片上传组件辅助-->
    <el-upload
      class="avatar-uploader"
      action=""
      name="img"
      :accept="uploadType"
      :show-file-list="false"
      :before-upload="beforeUpload">
    </el-upload>
    <!-- /图片上传组件辅助-->
  </div>
</template>
<script>

  import * as Quill from 'quill';
  import ForumPostUrls from "../../projects/dms/modules/console/urls/forum-post-urls";

  let fonts = ['Microsoft-YaHei', 'SimSun', 'SimHei', 'KaiTi', 'FangSong', 'Arial', 'Times-New-Roman', 'sans-serif'];
  let Font = Quill.import('formats/font');
  Font.whitelist = fonts; //将字体加入到白名单
  Quill.register(Font, true);

  const toolbarOptions = [
    ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
    [{size: ["small", false, "large", "huge"]}], // 字体大小
    [{font: fonts}], // 字体种类
    [{color: []}, {background: []}], // 字体颜色、字体背景颜色
    [{align: []}], // 对齐方式
    [{list: "ordered"}, {list: "bullet"}], // 有序、无序列表
    [{script: "sub"}, {script: "super"}], // 上标/下标
    [{indent: "-1"}, {indent: "+1"}], // 缩进
    ["image"] // 链接:link、图片:image、视频:video
  ];

  export default {
    name: "MyEditor",

    props: {
      value: {
        type: String,
        default: ''
      },
      width: {
        type: [Number, String]
      },
      height: {
        type: [Number, String]
      }
    },

    data() {
      return {
        content: '',
        editorWidth: this.width,
        editorHeight: this.height,
        editorOption: {
          placeholder: '',
          theme: 'snow',  // or 'bubble'
          modules: {
            toolbar: {
              container: toolbarOptions,  // 工具栏
              handlers: {
                'image': function (value) {
                  if (value) {
                    document.querySelector('.avatar-uploader input').click()
                  } else {
                    this.quill.format('image', false);
                  }
                }
              }
            }
          }
        },

        uploadLoading: false,
        uploadType: 'image/jpeg,image/jpg,image/gif,image/png,image/bmp',

        serverPath: ''
      };
    },

    computed: {
      _width_() {
        return this.editorWidth ? parseFloat(`${this.editorWidth}`.replace(/[^0-9,.]/g, "")) : undefined;
      },
      __width__() {
        return this._width_ ? this._width_ > 100 ? `${this._width_}px` : `${this._width_}%` : undefined;
      },
      _height_() {
        return this.editorHeight ? parseFloat(`${this.editorHeight}`.replace(/[^0-9,.]/g, "")) : undefined;
      },
      __height__() {
        return this._height_ ? this._height_ > 100 ? `${this._height_}px` : `${this._height_}%` : undefined;
      },
      style() {
        return {
          width: this.__width__,
          height: this.__height__
        };
      },
    },

    watch: {
      value(val) {
        this.content = val;
      },
      content(val) {
        this.$emit('input', val);
      }
    },

    methods: {
      onEditorBlur() {
        //失去焦点事件
        this.$emit('blur', this.serverPath);
      },
      onEditorFocus() {
        //获得焦点事件
      },
      onEditorChange() {
        //内容改变事件
        // Bus.$emit('getEditorCode',this.serverPath)
      },
      beforeUpload(file) {
        this.uploadLoading = true;

        const isJPEG = file.type === 'image/jpeg';
        const isJPG = file.type === 'image/jpg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';

        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$Message('上传图片大小不能超过2MB!');
          return;
        }

        if (!isJPEG && !isJPG && !isGIF && !isPNG && !isBMP) {
          this.$Message('图片格式不正确，请选择jpeg/jpg/png/gif/bmp格式文件。');
          return;
        }


        let formData = new FormData();
        formData.append("file", file);

        this.$Ajax.post(ForumPostUrls.post.postImage, formData)
          .success(true, data => {
            let imgUrl = `http://${data.records.staticServerPath}${data.records.staticPathPrefix}/${data.records.uploadResult.fileRelativeSavePath}${data.records.uploadResult.fileSaveFullName}`;
            let quill = this.$refs.quillEditor.quill; // 图片服务器返回的数据
            let length = quill.getSelection().index;  // 获取光标所在位置

            quill.insertEmbed(length, 'image', imgUrl); // 插入图片服务器返回的图片地址
            quill.setSelection(length + 1); // 调整光标到最后

            this.uploadLoading = false;

            this.serverPath = data.records.staticServerPath + data.records.staticPathPrefix;
          })
          .fail(() => {
            this.uploadLoading = false;
            this.$Message({content: "图片上传失败，请重新上传。", duration: 1000});
          })
          .finally(() => this.uploadLoading = false);

        return false;
      }
    },

    mounted: function () {
      this.$refs.quillEditor.$el.children[1].style.height = this.editorHeight - 42 + 'px';
    }
  };
</script>

<style>
  .editor {
    line-height: normal !important;
    overflow: hidden;
  }

  .editor .ql-container {
    font-size: 14px;
  }

  .ql-snow .ql-tooltip[data-mode=link]::before {
    content: "请输入链接地址:";
  }

  .ql-snow .ql-tooltip.ql-editing a.ql-action::after {
    border-right: 0px;
    content: '保存';
    padding-right: 0px;
  }

  .ql-snow .ql-tooltip[data-mode=video]::before {
    content: "请输入视频地址:";
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item::before {
    content: '14px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=small]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=small]::before {
    content: '10px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=large]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=large]::before {
    content: '18px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=huge]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=huge]::before {
    content: '32px';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item::before {
    content: '文本';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
    content: '标题1';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
    content: '标题2';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
    content: '标题3';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
    content: '标题4';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
    content: '标题5';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
    content: '标题6';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item::before {
    content: '标准字体';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=serif]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=serif]::before {
    content: '衬线字体';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=monospace]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=monospace]::before {
    content: '等宽字体';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=SimSun]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=SimSun]::before {
    content: "宋体";
    font-family: "SimSun";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=SimHei]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=SimHei]::before {
    content: "黑体";
    font-family: "SimHei";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=Microsoft-YaHei]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=Microsoft-YaHei]::before {
    content: "微软雅黑";
    font-family: "Microsoft YaHei";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=KaiTi]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=KaiTi]::before {
    content: "楷体";
    font-family: "KaiTi";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=FangSong]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=FangSong]::before {
    content: "仿宋";
    font-family: "FangSong";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=Arial]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=Arial]::before {
    content: "Arial";
    font-family: "Arial";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=Times-New-Roman]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=Times-New-Roman]::before {
    content: "Times New Roman";
    font-family: "Times New Roman";
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=sans-serif]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=sans-serif]::before {
    content: "sans-serif";
    font-family: "sans-serif";
  }

  .ql-font-SimSun {
    font-family: "SimSun";
  }

  .ql-font-SimHei {
    font-family: "SimHei";
  }

  .ql-font-Microsoft-YaHei {
    font-family: "Microsoft YaHei";
  }

  .ql-font-KaiTi {
    font-family: "KaiTi";
  }

  .ql-font-FangSong {
    font-family: "FangSong";
  }

  .ql-font-Arial {
    font-family: "Arial";
  }

  .ql-font-Times-New-Roman {
    font-family: "Times New Roman";
  }

  .ql-font-sans-serif {
    font-family: "sans-serif";
  }
</style>
