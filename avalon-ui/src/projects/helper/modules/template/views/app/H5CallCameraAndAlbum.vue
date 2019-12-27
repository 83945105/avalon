<template>
  <div>
    <h4>上传</h4>
    <div>
      <span type="primary" class="wrapper">
          <label class="btn" for="fileUpload">上传</label>
      </span>
      <input type="file" id="fileUpload" style="position:absolute; clip:rect(0 0 0 0);"
             @change="upload">
    </div>
  </div>
</template>

<script>

  export default {

    name: "h5-call-camera-and-album",

    methods: {
      upload(e) {
        let file = e.target.files[0];
        let formData = new FormData();
        formData.append('file', file);
        console.log('正在上传视频...');
        this.doUpload(formData);
      },
      doUpload(formData) {
        alert(JSON.stringify(formData));
      }
    }

  }
</script>

<style scoped>
  input.file {
    position: relative;
    -moz-opacity: 0;
    filter: alpha(opacity:0);
    opacity: 0;
    z-index: 2;
  }

  .wrapper {
    color: #fff;
    margin-top: 5px;
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background: #31b0d5 none;
    border: 1px solid transparent;
    border-radius: 4px;
  }
</style>
