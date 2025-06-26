#!/usr/bin/env python3
"""
Script để test gửi thông báo đẩy từ server sử dụng Firebase Cloud Messaging
"""

import requests
import json
import sys

# Firebase Server Key - Lấy từ Firebase Console > Project Settings > Cloud Messaging
FIREBASE_SERVER_KEY = "YOUR_SERVER_KEY_HERE"

# FCM endpoint
FCM_URL = "https://fcm.googleapis.com/fcm/send"

def send_notification(device_token, title, body, data=None):
    """
    Gửi thông báo đến device cụ thể
    
    Args:
        device_token (str): FCM token của device
        title (str): Tiêu đề thông báo
        body (str): Nội dung thông báo
        data (dict): Dữ liệu tùy chỉnh
    """
    
    headers = {
        'Authorization': f'key={FIREBASE_SERVER_KEY}',
        'Content-Type': 'application/json'
    }
    
    payload = {
        'to': device_token,
        'notification': {
            'title': title,
            'body': body,
            'sound': 'default',
            'badge': '1'
        },
        'data': data or {},
        'priority': 'high'
    }
    
    try:
        response = requests.post(FCM_URL, headers=headers, data=json.dumps(payload))
        
        if response.status_code == 200:
            result = response.json()
            if result.get('success') == 1:
                print("✅ Thông báo gửi thành công!")
                print(f"Message ID: {result.get('results', [{}])[0].get('message_id')}")
            else:
                print("❌ Gửi thông báo thất bại!")
                print(f"Error: {result}")
        else:
            print(f"❌ HTTP Error: {response.status_code}")
            print(f"Response: {response.text}")
            
    except Exception as e:
        print(f"❌ Lỗi: {e}")

def send_topic_notification(topic, title, body, data=None):
    """
    Gửi thông báo đến topic
    
    Args:
        topic (str): Tên topic (ví dụ: 'general', 'news')
        title (str): Tiêu đề thông báo
        body (str): Nội dung thông báo
        data (dict): Dữ liệu tùy chỉnh
    """
    
    headers = {
        'Authorization': f'key={FIREBASE_SERVER_KEY}',
        'Content-Type': 'application/json'
    }
    
    payload = {
        'to': f'/topics/{topic}',
        'notification': {
            'title': title,
            'body': body,
            'sound': 'default',
            'badge': '1'
        },
        'data': data or {},
        'priority': 'high'
    }
    
    try:
        response = requests.post(FCM_URL, headers=headers, data=json.dumps(payload))
        
        if response.status_code == 200:
            result = response.json()
            if result.get('success') == 1:
                print("✅ Thông báo topic gửi thành công!")
                print(f"Message ID: {result.get('results', [{}])[0].get('message_id')}")
            else:
                print("❌ Gửi thông báo topic thất bại!")
                print(f"Error: {result}")
        else:
            print(f"❌ HTTP Error: {response.status_code}")
            print(f"Response: {response.text}")
            
    except Exception as e:
        print(f"❌ Lỗi: {e}")

def main():
    print("🚀 SmartTasks - Push Notification Tester")
    print("=" * 50)
    
    # Kiểm tra server key
    if FIREBASE_SERVER_KEY == "YOUR_SERVER_KEY_HERE":
        print("❌ Vui lòng cập nhật FIREBASE_SERVER_KEY trong script!")
        print("Lấy Server Key từ: Firebase Console > Project Settings > Cloud Messaging")
        return
    
    while True:
        print("\n📋 Chọn loại thông báo:")
        print("1. Gửi thông báo đến device cụ thể")
        print("2. Gửi thông báo đến topic")
        print("3. Thoát")
        
        choice = input("\nNhập lựa chọn (1-3): ").strip()
        
        if choice == "1":
            device_token = input("Nhập FCM Device Token: ").strip()
            title = input("Nhập tiêu đề: ").strip()
            body = input("Nhập nội dung: ").strip()
            
            # Dữ liệu tùy chỉnh
            task_id = input("Nhập Task ID (optional): ").strip()
            data = {"taskId": task_id} if task_id else None
            
            send_notification(device_token, title, body, data)
            
        elif choice == "2":
            topic = input("Nhập tên topic (ví dụ: general): ").strip()
            title = input("Nhập tiêu đề: ").strip()
            body = input("Nhập nội dung: ").strip()
            
            # Dữ liệu tùy chỉnh
            task_id = input("Nhập Task ID (optional): ").strip()
            data = {"taskId": task_id} if task_id else None
            
            send_topic_notification(topic, title, body, data)
            
        elif choice == "3":
            print("👋 Tạm biệt!")
            break
            
        else:
            print("❌ Lựa chọn không hợp lệ!")

if __name__ == "__main__":
    main() 