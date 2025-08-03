import React, { useState } from 'react';
import CourseDetails from './components/CourseDetails';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import './App.css';

function App() {
  const [showCourse, setShowCourse] = useState(true);
  const [showBook, setShowBook] = useState(true);
  const [showBlog, setShowBlog] = useState(true);

  return (
    <div className="App">
      <h1>Blogger App</h1>

      <div className="button-group">
        <button onClick={() => setShowCourse(!showCourse)}>Toggle Course</button>
        <button onClick={() => setShowBook(!showBook)}>Toggle Book</button>
        <button onClick={() => setShowBlog(!showBlog)}>Toggle Blog</button>
      </div>

      <div className="container">
        {showCourse && <CourseDetails />}
        {showBook && <BookDetails />}
        {showBlog && <BlogDetails />}
      </div>
    </div>
  );
}

export default App;
