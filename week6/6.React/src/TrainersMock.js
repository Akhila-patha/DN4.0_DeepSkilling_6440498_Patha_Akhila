import Trainer from './Trainer';

const trainers = [
    new Trainer(1, "John Doe", "john@example.com", "9876543210", "Java", ["Core Java", "Spring Boot", "Hibernate"]),
    new Trainer(2, "Jane Smith", "jane@example.com", "9876543211", "JavaScript", ["React", "Node.js", "Express"]),
    new Trainer(3, "Mark Taylor", "mark@example.com", "9876543212", "Python", ["Django", "Flask", "Data Science"]),
    new Trainer(4, "Emily Davis", "emily@example.com", "9876543213", "Cloud", ["AWS", "Azure", "DevOps"]),
    new Trainer(5, "Michael Brown", "michael@example.com", "9876543214", "Full Stack", ["React", "Node.js", "MongoDB"])
];

export default trainers;
