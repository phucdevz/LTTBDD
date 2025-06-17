package com.example.he_thong_quan_li_thu_vien.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.he_thong_quan_li_thu_vien.ui.theme.He_thong_quan_li_thu_vienTheme

// Data classes
data class Book(val id: String, val name: String)
data class Student(val id: String, val name: String, val borrowedBookIds: MutableSet<String>)

@Composable
fun LibraryManagerContent() {
    val allBooks = remember {
        listOf(
            Book("1", "Sách 01"), Book("2", "Sách 02"), Book("3", "Sách 03"), Book("4", "Sách 04"),
            Book("5", "Sách 05"), Book("6", "Sách 06"), Book("7", "Sách 07"), Book("8", "Sách 08"),
            Book("9", "Sách 09"), Book("10", "Sách 10")
        )
    }

    val allStudents = remember {
        mutableStateListOf(
            Student("sv1", "Nguyen Van A", mutableSetOf("1", "3")),
            Student("sv2", "Nguyen Thi B", mutableSetOf("2")),
            Student("sv3", "Nguyen Duc Luong", mutableSetOf("8", "10")),
            Student("sv4", "Tran Van C", mutableSetOf("4", "5", "6")),
            Student("sv5", "Le Thi D", mutableSetOf("7", "9"))
        )
    }

    var studentInput by remember { mutableStateOf("") }
    var selectedStudent by remember { mutableStateOf<Student?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val checkedBooks = remember { mutableStateMapOf<String, Boolean>() }

    fun updateCheckedBooks() {
        val borrowed = selectedStudent?.borrowedBookIds ?: emptySet()
        checkedBooks.clear()
        allBooks.forEach { book ->
            checkedBooks[book.id] = borrowed.contains(book.id)
        }
    }

    fun findStudentByName(name: String): Student? {
        return allStudents.find { it.name == name }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                "Hệ thống\nQuản lý Thư viện",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = studentInput,
                    onValueChange = {
                        studentInput = it
                        errorMessage = null
                    },
                    placeholder = { Text("Nhập tên sinh viên") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    isError = errorMessage != null,
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val found = findStudentByName(studentInput)
                        if (found != null) {
                            selectedStudent = found
                            errorMessage = null
                            updateCheckedBooks()
                        } else {
                            selectedStudent = null
                            errorMessage = "Không tìm thấy sinh viên."
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0052CC))
                ) {
                    Text("Thay đổi")
                }
            }

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Text(
            "Danh sách sách:",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 24.dp, top = 4.dp, bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFECECEC))
                .padding(8.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(allBooks.sortedByDescending { checkedBooks[it.id] == true }) { book ->
                    val checked = checkedBooks[book.id] ?: false
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .background(Color.White, shape = RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .clickable { checkedBooks[book.id] = !checked },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checkedBooks[book.id] = it },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF880E4F))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(book.name, fontSize = 16.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                selectedStudent?.let { student ->
                    val newBorrowed = checkedBooks.filterValues { it }.keys
                    student.borrowedBookIds.clear()
                    student.borrowedBookIds.addAll(newBorrowed)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0052CC)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Thêm", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        NavigationBar(containerColor = Color.White) {
            NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Quản lý") })
            NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Book, null) }, label = { Text("DS Sách") })
            NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Sinh viên") })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUI() {
    He_thong_quan_li_thu_vienTheme {
        LibraryManagerContent()
    }
}
