<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="roomid">房间号:</label>
        <input type="text" id="roomid" v-model="formData.roomid">
      </div>
      <div>
        <label for="csrfToken">TOKEN:</label>
        <input type="text" id="csrfToken" v-model="formData.csrfToken">
      </div>
      <div>
        <label for="csrf">csrf:</label>
        <textarea id="csrf" v-model="formData.csrf"></textarea>
      </div>
      <div>
        <label for="cookie">COOKIE:</label>
        <textarea id="cookie" v-model="formData.cookie"></textarea>
      </div>
      <div>
        <label for="msg">弹幕内容:</label>
        <textarea id="msg" v-model="formData.msg"></textarea>
      </div>
      <div>
        <label for="sendNumber">发送数量:</label>
        <textarea id="sendNumber" v-model="formData.sendNumber"></textarea>
      </div>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        'msg': '',
        'roomid': '',
        'sendNumber': '',
        'csrf': '',
        'csrfToken': '',
        'cookie': ''
      }
    };
  },
  methods: {
    submitForm() {

      console.log("你好");
      console.log(this.formData);
      axios.post('/api/v1/danmu', this.formData)
          .then(response => {

            console.log(response.data);
          })
          .catch(error => {

            console.error('Error submitting form:', error);
          });
    }
  }
};
</script>

<style scoped>
div {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>

<style scoped>
div {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>